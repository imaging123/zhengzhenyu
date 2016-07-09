package org.sdust.searchEngine.util;

import org.sdust.searchEngine.util.Constants;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzy on 2016/3/27.
 */
public class FileProcessor {

    /**
     * @param file      要处理的文件
     * @param outputDir 处理后的输出目录
     */
    public static void preProcess(File file, String outputDir) {
        if (!new File(outputDir).exists())
            new File(outputDir).mkdirs();
        if (!outputDir.endsWith("/") && !outputDir.endsWith("\\")){
            outputDir = outputDir+"/";
        }
        try {
            splitToSmallFiles(characterProcess(file,outputDir+"output.txt"),outputDir);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param file     要处理的文件
     * @param destFile 输出到指定文件
     * @return
     */
    public static File characterProcess(File file, String destFile) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(destFile));

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = null;

        while ((line = br.readLine()) != null) {
            if (!line.equals("\r\n")) {
                String newLine = replace(line);//调用replace方法替换所有的全角字符
                bw.write(newLine);
                bw.newLine();
            }
        }
        br.close();
        bw.close();
        return new File(destFile);
    }

    /**
     *
     * @param file
     * @param outputPath
     */
    public static void splitToSmallFiles(File file,String outputPath) throws Exception{
        int filePointer = 0;


        if (!outputPath.endsWith("/") && !outputPath.endsWith("\\")){
            outputPath = outputPath+"/";
        }

        BufferedWriter bw = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            sb.append(line).append("\r\n");
            if (sb.toString().length() >= Constants.MAX_SIZE){
                bw = new BufferedWriter(new FileWriter(outputPath+ "textfile" +filePointer+".txt"));
                bw.write(sb.toString());
                bw.close();
                filePointer++;
                sb = new StringBuilder();
            }
        }

        if (!sb.toString().equals("")){
            bw =  new BufferedWriter(new FileWriter(outputPath+ "textfile" +filePointer+".txt"));
            bw.write(sb.toString());
            bw.close();
        }

    }

    /**
     * 全角半角转换
     * @param line
     * @return
     */
    private static String replace(String line) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("，",",");
        map.put("。",".");
        map.put("《","<");
        map.put("》",">");
        map.put("〈","<");
        map.put("〉",">");
        map.put("〔","[");
        map.put("〕","]");
        map.put("？","?");
        map.put("“","\"");
        map.put("”","\"");
        map.put("‘","'");
        map.put("’","'");
        map.put("、",",");
        map.put("（","(");
        map.put("）",")");
        map.put("——","_");
        map.put("【","[");
        map.put("】","]");
        map.put("！","!");
        map.put("~","~");
        map.put("：",":");

        int length = line.length();
        for (int i = 0;i<length;i++){
            String character = line.substring(i,i+1);
            if (map.containsKey(character))
                line.replace(character,map.get(character));
        }
        return line;
    }


    public static void main(String[] args) throws Exception {
        String inputFile = "E:\\searchEngine\\Thesuraus.txt";
        String outputDir = "E:\\ideafile\\searchEngine\\src\\main\\resources\\textfile\\";
//
        splitToSmallFiles(new File(inputFile), outputDir);
    }
}
