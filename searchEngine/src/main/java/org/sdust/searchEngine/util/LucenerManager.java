package org.sdust.searchEngine.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * lucene管理器单例
 * Created by zzy on 2016/5/16.
 */

public class LucenerManager {
    private static LucenerManager instance = null;
    private IndexSearcher searcher;
    private IndexWriter writer;
    private LucenerManager(){};

    private static synchronized void synInit(){
        if (instance == null){
            instance = new LucenerManager();
        }
    }

    public static LucenerManager getInstance(){
        if (instance == null){
            synInit();
        }
        return instance;
    }


    /**
     * 获取IndexSearcher
     * @param indexPath
     * @param analyzer
     * @return
     */
    public IndexSearcher getSearcher(String indexPath,Analyzer analyzer) throws Exception {
            Directory directory = FSDirectory.open(new File(indexPath));
            IndexReader reader = DirectoryReader.open(directory);
            searcher = new IndexSearcher(reader);
        return searcher;
    }

    /**
     * 获取IndexWriter
     * @param themeIndexPath
     * @param analyzer
     * @return
     */
    public IndexWriter getWriter(String themeIndexPath,Analyzer analyzer) throws Exception {
        Directory directory = null;
            directory = FSDirectory.open(new File(themeIndexPath));
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44,analyzer);
            writer = new IndexWriter(directory,iwc);
       return writer;
    }


}
