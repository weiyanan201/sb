package com.wei.eduyang.util;

import com.alibaba.fastjson.JSONObject;

public class CommonUtil {

    //第一页为1
    //分页查询json处理
    //
    public static void convertPageSizeFromJson(JSONObject jsonObject){
        if (!jsonObject.containsKey(Constants.PAGE)){
            jsonObject.put(Constants.START,Constants.DEFAULT_START);
            jsonObject.put(Constants.SIZE,Constants.DEFAULT_SIZE);
        }else{
//            {total: 25, pageSize: 20, current: 2}
            JSONObject page = jsonObject.getJSONObject(Constants.PAGE);
            int current = page.getInteger(Constants.CURRENT);
            int size = page.getInteger(Constants.SIZE);
            int start = current==0?0:(current-1)*size;
            jsonObject.put(Constants.START,start);
            jsonObject.put(Constants.SIZE,Constants.DEFAULT_SIZE);
        }

    }
}
