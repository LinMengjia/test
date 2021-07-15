package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Create By Linmengjia on 2020/7/15.
 */
@RestController
public class TestController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello (){
        return "hello";
    }


    @RequestMapping(value = "/getRequestInfo",method= RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRequestInfo (HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> headerInfoMap = new HashMap<>();
        Map<String,String[]> requestInfo = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        try{
            Enumeration<String> headerNames = request.getHeaderNames();
            System.out.println("************  the Information of RequestHeader  **************");
            while (headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement();
                String headerInfo = request.getHeader(headerName);
                headerInfoMap.put(headerName,headerInfo);
                System.out.println(headerName+"："+headerInfo);
            }
            System.out.println();
            requestInfo = request.getParameterMap();
            System.out.println("************  the Information of Parameters  **************");
            for(String key : requestInfo.keySet()){
                System.out.println(key+"："+ Arrays.toString(requestInfo.get(key)));
            }
            System.out.println();
            map.put("headerInfoViews", headerInfoMap);
            map.put("parameterInfoViews", requestInfo);
            map.put("resultCode", "200");
        }catch (Exception e) {
            map.put("headerInfoViews", new HashMap<>());
            map.put("parameterInfoViews", new HashMap<>());
            map.put("resultCode", "500");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/getRequestParameter",method= RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRequestParameter (HttpServletRequest request, HttpServletResponse response)  {
        Map<String,String[]> requestInfo = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        try{
            requestInfo = request.getParameterMap();
            System.out.println("************  the Information of Parameters  **************");
            for(String key : requestInfo.keySet()){
                System.out.println(key+"："+ Arrays.toString(requestInfo.get(key)));
            }
            System.out.println();
            map.put("dataViews", requestInfo);
            map.put("resultCode", "200");
        }catch (Exception e) {
            map.put("dataViews", new HashMap<>());
            map.put("resultCode", "500");
            e.printStackTrace();
        }
        return map;
    }

}
