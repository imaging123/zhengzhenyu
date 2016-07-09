package org.sdust.searchEngine.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by zzy on 2016/5/15.
 */

public class ContextHelper {
    public static Multimap<String, String> synonymsList = ArrayListMultimap.create();
    public static List<String> sensitiveWords = new ArrayList<String>();

//    public static void main(String[] args) {
//        Multimap<String, String> multimap = ArrayListMultimap.create();
//
//        String path = "E:\\searchEngine\\textfile.txt";
//        String key = null;
//        String line = null;
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
//            while((line = br.readLine())!= null){
//                if (line.startsWith("@")){
//                    key = line.substring(1);
//                    continue;
//                }
//                multimap.put(key,line);
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
