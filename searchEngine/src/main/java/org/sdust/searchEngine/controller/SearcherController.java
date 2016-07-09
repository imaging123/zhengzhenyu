package org.sdust.searchEngine.controller;

import com.alibaba.fastjson.JSON;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Filter;
import org.sdust.searchEngine.entity.Page;
import org.sdust.searchEngine.service.SearcherService;
import org.sdust.searchEngine.util.Constants;
import org.sdust.searchEngine.util.ContextHelper;
import org.sdust.searchEngine.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by zzy on 2016/5/16.
 */

@RestController
@RequestMapping("/searcher")
public class SearcherController {

    @Autowired
    SearcherService searcher;

    @Autowired
    CacheManager manager;

    /**
     *处理result页面的ajax请求
     *@param map
     * @return
     */
    @RequestMapping(value = "/allResult",method = RequestMethod.POST)
    public ResponseMessage search(@RequestBody Map<String,String> map,Model model){
        ResponseMessage rsp = null;
        Cache cache = manager.getCache("message");
        Page page = null;

        int topNValue= 0;
        //清空缓存
         if(cache != null) cache.clear();
        if (map != null){
            //匹配topN=all
            if ("all".equals(map.get("topN"))){
                topNValue = Integer.MAX_VALUE;
            }else {
                topNValue = Integer.valueOf(map.get("topN"));
            }
            Long start =  System.currentTimeMillis();
            rsp = searcher.search(map.get("fieldName"),map.get("queryStr"),map.get("type"),topNValue);
            Long end = System.currentTimeMillis();
            if (rsp.getData() != null) {
                page = (Page) rsp.getData();
                page.setTime(end - start);
            }
        }
        rsp.setData(page);
       return rsp;
    }

    /**
     * 处理首页的get请求
     * @param queryStr
     * @param topN
     * @param type
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView searchFromIndex(String queryStr,String topN,String type,Model model) throws ParseException, UnsupportedEncodingException {
        ResponseMessage rsp = null;
        int topNValue = 0;
        Cache cache = manager.getCache("message");
        //清空缓存
        if (cache != null )cache.clear();

        //匹配topN=all
        if ("all".equals(topN)){
            topNValue = Integer.MAX_VALUE;
        }else {
            topNValue = Integer.valueOf(topN);
        }

        Long start =  System.currentTimeMillis();
        rsp = searcher.search("content",queryStr,type,topNValue);
        Long end = System.currentTimeMillis();
//        QueryWrapperFilter
//        StopFilter
        model.addAttribute("queryStr",queryStr);
        model.addAttribute("typeName", Constants.MAP.get(type));
        model.addAttribute("topN",topN);
        model.addAttribute("type",type);
        model.addAttribute("time",end-start);
        model.addAttribute("maxDocs", rsp.getData() != null ? ((Page) rsp.getData()).getTotalCount() : 0);

         return new ModelAndView("result","message", JSON.toJSON(rsp).toString());
    }

    @RequestMapping(value = "/part/{oldStr}",method = RequestMethod.POST)
    public ResponseMessage searchInAll(@PathVariable String oldStr,@RequestBody Map<String,String> map,Model model) throws ParseException {
        ResponseMessage rsp = null;
        Cache cache = manager.getCache("message");
        Page page = null;
        int topNValue = 0;
        if (cache != null )cache.clear();
        if (map != null){
            //匹配topN=all
            if ("all".equals(map.get("topN"))){
                topNValue = Integer.MAX_VALUE;
            }else {
                topNValue = Integer.valueOf(map.get("topN"));
            }
            Filter filter = searcher.getFilter(map.get("queryStr"),map.get("fieldName"));
            Long start =  System.currentTimeMillis();
            rsp = searcher.search(map.get("fieldName"),oldStr,map.get("type"),filter,topNValue);
            Long end = System.currentTimeMillis();
            if (rsp.getData() != null){
                page = (Page) rsp.getData();
                page.setTime(end-start);
            }
        }

        rsp.setData(page);
//        QueryWrapperFilter
//        StopFilter
        return rsp;
    }

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public ResponseMessage searchByPage(@RequestBody Map<String,String> map){
        ResponseMessage rsp = null;
        Page page1 = null;
        Cache cache = manager.getCache("message");
        if (cache != null) {
            rsp = cache.get(map.get("fieldName"), ResponseMessage.class);
        }
        if (rsp != null){
            page1 = (Page) rsp.getData();
        }
        page1.setCurrentNo(Integer.valueOf(map.get("page")));
        rsp.setData(page1);
        return rsp;
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
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(Constants.SENSITIVE_PATH)));
            while ((line = br.readLine()) != null) {
                ContextHelper.sensitiveWords.addAll(Arrays.asList(line.split("@")));
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SearcherService service = new SearcherService();
        ResponseMessage rsp = service.search("title","很好","news",1000);
        System.out.println(rsp.getData());
    }



}
