package org.sdust.searchEngine.service;

import org.sdust.searchEngine.util.Constants;
import org.sdust.searchEngine.util.ContextHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Created by zzy on 2016/5/15.
 */
@Component
public class InitSensitiveList extends WebApplicationObjectSupport {


    @Override
    protected void initServletContext(ServletContext servletContext) {
        super.initServletContext(servletContext);
        String key = null;
        String line = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(Constants.SENSITIVE_PATH)));
            while ((line = br.readLine()) != null) {
                ContextHelper.sensitiveWords.addAll(Arrays.asList(line.split("@")));
            }
            System.out.println(ContextHelper.sensitiveWords);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String key = null;
        String line = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(Constants.SENSITIVE_PATH)));
            while ((line = br.readLine()) != null) {
                ContextHelper.sensitiveWords.addAll(Arrays.asList(line.split("@")));
            }

            System.out.println(ContextHelper.sensitiveWords.contains("法轮"));
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
