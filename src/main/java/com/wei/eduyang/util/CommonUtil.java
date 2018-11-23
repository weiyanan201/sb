package com.wei.eduyang.util;

import com.alibaba.fastjson.JSONObject;

public class CommonUtil {

    //第一页为0
    //分页查询json处理
    public static void convertPageSizeFromJson(JSONObject jsonObject){
        if (!jsonObject.containsKey(Constants.PAGE)){
            jsonObject.put(Constants.START,0);
            jsonObject.put(Constants.SIZE,Constants.DEFAULT_SIZE);
        }else{
            int page = jsonObject.getInteger(Constants.PAGE);
            int size = jsonObject.getInteger(Constants.SIZE);
            int start = page==0?0:page*size;
            jsonObject.put(Constants.START,start);
            jsonObject.put(Constants.SIZE,Constants.DEFAULT_SIZE);
        }

    }
}
