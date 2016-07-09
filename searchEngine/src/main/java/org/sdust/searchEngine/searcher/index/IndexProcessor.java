package org.sdust.searchEngine.searcher.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.sdust.searchEngine.util.Constants;
import org.sdust.searchEngine.util.LucenerManager;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by zzy on 2016/3/27.
 */
public class IndexProcessor {
    //存放索引文件的位置
    private String INDEX_STORE_PATH = Constants.INDEX_STORE_PATH;
    private LucenerManager manager = LucenerManager.getInstance();


    /**
     * 创建索引
     *
     * @param inputDir
     */
    public void createIndex(String inputDir) {

        File fileDir = new File(inputDir);//数据源文件夹
        String subDirPath = null;//数据源主题文件夹路径
        String themeIndexStorePath = null;//主题文件对应的主题索引目录
        //如果文件不存在
        if (!fileDir.exists()) fileDir.mkdirs();

        List<File> dirList = new ArrayList<File>();//主题文件夹

        dirList.addAll(Arrays.asList(fileDir.listFiles()));

        for (File file : dirList) {
            try {
                themeIndexStorePath = INDEX_STORE_PATH + "\\" + file.getName();
                File indexFile = new File(themeIndexStorePath);
                if (!indexFile.exists())
                    indexFile.mkdir();
                //IKAnalyzer作为分词工具
                IndexWriter writer = manager.getWriter(themeIndexStorePath,new IKAnalyzer());
                if (file.isFile())
                    throw new Exception(file.getName() + ":this must be a directory");
                else {
                    subDirPath = inputDir + "\\" + file.getName();
                    File subDir = new File(subDirPath);
                    for (File themeFile : subDir.listFiles()) {
                        //获取文件名称
                        String fileName = themeFile.getName();
                        if (themeFile.isDirectory()) throw new Exception(fileName + ":this must be a file");

                        BufferedReader br = new BufferedReader(new FileReader(themeFile));
                        //判断是否是txt文件
                        if (fileName.endsWith(".txt")) {
                            //创建一个document对象
                            Document doc = new Document();
                            Map<String,String> content = loadFileToString(themeFile);
                            String digest = content.get("digest");
                            String text = content.get("text");

                            //为url创建filed
                            Field field = new TextField("url", br.readLine(), Field.Store.YES);

                            doc.add(field);
                            //为标题创建一个Field
                            field = new TextField("title", br.readLine(), Field.Store.YES);
                            doc.add(field);

                            //为文件内容创建一个field
                            field = new TextField("content", text,Field.Store.NO);
                            doc.add(field);

                            //为文件path创建一个field
                            field = new TextField("filePath", subDirPath+"\\"+fileName, Field.Store.YES);
                            doc.add(field);

                            //为摘要创建field
                            field = new TextField("digest",digest,Field.Store.YES);
                            doc.add(field);

                            writer.addDocument(doc);

                            br.close();
                        } else {
                            //todo:如果是其他类型的文件
                        }
                    }
                }
                //关闭IndexWriter
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String,String> loadFileToString(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            StringBuilder digest = new StringBuilder();
            int count = 0;
            Map<String,String> map = new HashMap<>();
            while ((line = br.readLine()) != null) {
                count++;
                if (count == 3){
                    digest.append(line.length()>400?line.substring(0,400):line);
                }
                sb.append(line);
            }
            map.put("digest",digest.toString());
            map.put("text",sb.toString());

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        System.out.println("hello.txt".endsWith(".txt"));
        IndexProcessor processor = new IndexProcessor();
        processor.createIndex("E:\\ideafile\\files");
//        try(LineNumberReader lnr = new LineNumberReader(new FileReader(new File("E:\\ideafile\\searchEngine\\src\\main\\resources\\files\\news\\file20160513110842.txt")));){
//            String line = null;
//            int count = 0;
//            while((line = lnr.readLine())!= null){
//                count++;
//                System.out.println(line.length());
//            }
//            System.out.println(count);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

}
