package org.sdust.searchEngine.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.sdust.searchEngine.entity.DocumentEntity;
import org.sdust.searchEngine.entity.Page;
import org.sdust.searchEngine.util.*;
import org.sdust.searchEngine.util.LucenerManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zzy on 2016/5/17.
 */
@Service
public class SearcherService {
    private LucenerManager manager = LucenerManager.getInstance();

//    private IKAnalyzer analyzer = new IKAnalyzer(true);;//分析器

    private Analyzer analyzer = new IKAnalyzer();
    private TokenStream ts;


    /**
     * 获取searcher
     * @param indexPath
     * @return
     */
//    private IndexSearcher getSearcher(String indexPath) {
//        IndexSearcher searcher = null;
//        try {
//            Directory directory = FSDirectory.open(Paths.get(indexPath));
//            IndexReader reader = DirectoryReader.open(directory);
//            searcher = new IndexSearcher(reader);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return searcher;
//
//    }

    /**
     * search without filter
     *
     * @param fieldName
     * @param inputStr
     * @param type
     * @param topN
     * @return
     */
    @Cacheable(value = "message", key = "#fieldName")
    public ResponseMessage search(String fieldName, String inputStr, String type, int topN) {
        return this.search(fieldName, inputStr, type, null, topN);
    }

//    /**
//     * 默认查询方法
//     * @param inputStr
//     * @param type
//     * @param topN
//     * @return
//     */
//    public ResponseMessage search(String inputStr, String type, int topN){
//        return  this.search("content",inputStr,type,null,topN);
//    }


    /**
     * @param fieldName field
     * @param inputStr  用户输入的查询语句
     * @param type      查询主题
     * @param topN      得分靠前文档数量
     * @return
     */
    @Cacheable(value = "message", key = "#fieldName")
    public ResponseMessage search(String fieldName, String inputStr, String type, Filter filter, int topN) {
        Page page = new Page();
        ResponseMessage rsp = null;
        Query query = null;
        ScoreDoc[] hits = null;

        List<DocumentEntity> docEntities = new ArrayList<DocumentEntity>();//检索结果
        String indexPath = Constants.INDEX_STORE_PATH + "\\" + type;//查询主题对应索引路径
        IndexSearcher searcher = null;

        try {
            searcher = manager.getSearcher(indexPath, analyzer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessageFactory.error(StatusMessage.MSG_00002);
        }

        if (StringUtils.isBlank(inputStr))
            return null;
        //todo:preProcess
        rsp = preProcess(inputStr);
        if (rsp.hasError()) {
            return rsp;
        }
        //todo:search
        String queryStr = (String) rsp.getData();
        try {
            query = getQuery(fieldName, queryStr);
            hits = getScoreDocs(searcher, query, filter, topN);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessageFactory.error();
        }
        //高亮显示设置
        Highlighter highlighter = null;
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<em style=\"color:red\"><b>", "</b></em>");
        Highlighter highlighterTitle = null;
        SimpleHTMLFormatter formatTitle = new SimpleHTMLFormatter("<em style=\"color:#c60a00\">", "</em>");

        highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
        highlighterTitle = new Highlighter(formatTitle, new QueryScorer(query));
        highlighter.setTextFragmenter(new SimpleFragmenter(200));//这个200是指定关键字字符串的context
        //的长度，你可以自己设定，因为不可能返回整篇正文内容
        highlighterTitle.setTextFragmenter(new SimpleFragmenter(20));

        Document doc = null;
        int totalCount = hits.length;//查询结果总数
        for (int i = 0; i < hits.length; i++) {
            try {
                doc = searcher.doc(hits[i].doc);

                DocumentEntity docEntity = new DocumentEntity();
                //设置标题
                String title = doc.get("title");
                ts = analyzer.tokenStream("title", title);
                String forMatt = highlighterTitle.getBestFragment(ts, title);
                if (StringUtils.isBlank(forMatt)) {
                    docEntity.setTitle(title.length() > 20 ? title.substring(0, 20) + "..." : title);
                } else {
                    docEntity.setTitle(forMatt);
                }

                //设置摘要
                String digest = doc.get("digest");
                ts = analyzer.tokenStream("digest", digest);
                forMatt = highlighter.getBestFragment(ts, digest);
                if (StringUtils.isBlank(forMatt)) {
                    docEntity.setDigest(digest.length() > 200 ? (digest.substring(0, 200)) + "..." : (digest + "..."));
                } else {
                    docEntity.setDigest(forMatt + "...");
                }

                docEntity.setUrl(doc.get("url"));
                docEntity.setFilePath(doc.get("filePath"));

                docEntities.add(docEntity);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseMessageFactory.error();
            }
        }

        page.setData(docEntities);
        page.setTotalCount(totalCount);
        page.setMaxDocs(searcher.getIndexReader().maxDoc());

        return ResponseMessageFactory.successData(page);
    }

    /**
     * 字符串预处理:过滤敏感词，匹配同义词
     *
     * @param inputStr
     */
    private ResponseMessage preProcess( String inputStr) {
        //去除空格
        String newStr = inputStr.replaceAll(" ", "");
        //使用IKSegmenter进行分词
        String tokenStr = null;
        String result = " ";
        IKSegmenter ik = new IKSegmenter(new StringReader(newStr), true);//使用智能分词
        Lexeme lexeme = null;
        try {
            while ((lexeme = ik.next()) != null) {
                tokenStr = lexeme.getLexemeText();
                if (ContextHelper.sensitiveWords.contains(tokenStr)) {//token是敏感词
                    return ResponseMessageFactory.error(StatusMessage.MSG_00001);
                } else if (ContextHelper.synonymsList.containsKey(tokenStr)) {//token存在同义词
                    Collection<String> list = ContextHelper.synonymsList.get(tokenStr);
                    String tempStr = " ";
                    for (String str : list) {
                        tempStr += str + " ";
                    }
//                    newStr.replaceAll(tokenStr, tempStr+tokenStr);
                    tokenStr += tempStr;
                }
                result += tokenStr;
            }
            System.out.println(result);
            return ResponseMessageFactory.successData(result);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseMessageFactory.error();
        }
    }

    private ResponseMessage preProcess1(String fieldName, String inputStr) {
        //去除空格
        String newStr = inputStr.replaceAll(" ", "");
        //使用IKSegmenter进行分词
        String tokenStr = null;
        try {
            ts = analyzer.tokenStream(fieldName, new StringReader(newStr));
            CharTermAttribute chara = ts.addAttribute(CharTermAttribute.class);
            while (ts.incrementToken()) {
                tokenStr = chara.toString();
                if (ContextHelper.sensitiveWords.contains(tokenStr)) {//token是敏感词
                    return ResponseMessageFactory.error(StatusMessage.MSG_00001);
                } else if (ContextHelper.synonymsList.containsKey(tokenStr)) {//token存在同义词
                    Collection<String> list = ContextHelper.synonymsList.get(tokenStr);
                    String tempStr = " ";
                    for (String str : list) {
                        tempStr += str + " ";
                    }
                    newStr.replace(tokenStr, tempStr + tokenStr);
                }
            }
            return ResponseMessageFactory.successData(newStr);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseMessageFactory.error();
        } finally {
            try {
                ts.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Query getQuery(String fieldName, String queryStr) throws Exception {
        QueryParser parser = new QueryParser(Version.LUCENE_44, fieldName, analyzer);
        Query query = null;
        query = parser.parse(queryStr);

        return query;
    }

    public Filter getFilter(String inputStr, String fieldName) {
        QueryParser parser = new QueryParser(Version.LUCENE_44, fieldName, analyzer);
        ResponseMessage rsp = preProcess(inputStr);
        String queryStr = (String) rsp.getData();
        Filter filter = null;
        try {
            Query query = parser.parse(queryStr);
            filter = new QueryWrapperFilter(query);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return filter;
    }

    /**
     * 获取查询结果
     *
     * @param searcher
     * @param query
     * @param topN
     * @return
     */
    private ScoreDoc[] getScoreDocs(IndexSearcher searcher, Query query, Filter filter, int topN) throws IOException {
        ScoreDoc[] docs = null;
        TopDocs topDocs = null;
        if (filter != null)
            topDocs = searcher.search(query, filter, topN);
        else
            topDocs = searcher.search(query, topN);
        docs = topDocs.scoreDocs;
        return docs;

    }

//    public static void main(String[] args) throws IOException{
//       TokenStream ts = new PaodingAnalyzer().tokenStream("content",new StringReader("这就是爱"));
//        CharTermAttribute chara = ts.addAttribute(CharTermAttribute.class);
//        while(ts.incrementToken()){
//            System.out.println(chara);
//            System.out.println(chara.toString());
//        }
//
//
//    }

    public static void main(String[] args) {
        String str = "计算机";
        String tar = "计算机";
        String dest = "程序设计语言 电脑 电子计算机 计算机网络 计算机病毒 计算机程序 软件 台式计算机 微电脑 微型计算机 硬件 中文计算机 终端设备 计数器 计算尺 计算器 算尺 算盘 ";

        System.out.println(str.replace(tar, dest + tar));
    }
}
