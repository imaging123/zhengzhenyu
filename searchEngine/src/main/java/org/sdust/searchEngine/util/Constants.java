package org.sdust.searchEngine.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzy on 2016/3/27.
 */
public final class Constants {
    public static final String INDEX_STORE_PATH = "E:\\ideafile\\searchEngine\\src\\main\\resources\\indexs";//索引文件存储位置
    public static final String SYNONYMS_PATH = "E:\\ideafile\\searchEngine\\src\\main\\resources\\textfile\\synonyms.txt";
    public static final String SENSITIVE_PATH = "E:\\ideafile\\searchEngine\\src\\main\\resources\\textfile\\sensitive_words.txt";
    public static final int MAX_SIZE = 10240;//大文件拆成小文件的大小
    public static final Map<String,String> MAP = new HashMap<String,String>();

    static {
        MAP.put("news","新闻");
        MAP.put("book","图书");
        MAP.put("blog","博客");
        MAP.put("film","电影");
    }
}
