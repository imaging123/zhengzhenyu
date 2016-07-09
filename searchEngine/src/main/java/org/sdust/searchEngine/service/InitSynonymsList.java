package org.sdust.searchEngine.service;

import org.sdust.searchEngine.util.Constants;
import org.sdust.searchEngine.util.ContextHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by zzy on 2016/5/15.
 */
@Component
public class InitSynonymsList extends WebApplicationObjectSupport {

    @Override
    protected void initServletContext(ServletContext servletContext) {
        super.initServletContext(servletContext);
        String key = null;
        String line = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(Constants.SYNONYMS_PATH)));
            while((line = br.readLine())!= null){
                if (line.startsWith("@")){
                    key = line.substring(1);
                    continue;
                }
                ContextHelper.synonymsList.put(key, line);
            }
//            System.out.println(ContextHelper.synonymsList);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        String key = null;
        String line = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(Constants.SYNONYMS_PATH)));
            while((line = br.readLine())!= null){
                if (line.startsWith("@")){
                    key = line.substring(1);
                    continue;
                }
                ContextHelper.synonymsList.put(key, line);
            }
            System.out.println(ContextHelper.synonymsList.get("计算机"));
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
