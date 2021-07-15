package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Create By Linmengjia on 2020/7/23.
 */
@RestController
public class ConvertController {
    private static Logger log = LoggerFactory.getLogger(ConvertController.class);

    @RequestMapping(value = "apmApiRequest",method = RequestMethod.POST)
    public void convertPostToGet(HttpServletResponse response, @RequestBody String body){
        JSONObject in = JSONObject.fromObject(body);
        log.info("入参：" + in.toString());
        JSONObject out = new JSONObject();
        if (containsKeys(in, "Uri", "Product", "Method", "Params", "Bodys")) {
            String product = (String) in.get("Product");



        }

    }


    public static boolean containsKeys(JSONObject obj, String... keys) {
        log.info("参数：" + obj.toString());
        if (obj != null) {
            for (String key : keys) {
                if (!obj.containsKey(key)) {
                    log.error("缺少必要参数： " + key);
                    return false;
                }
            }
        } else {
            log.error("参数不能为空！");
            return false;
        }
        return true;
    }
}
