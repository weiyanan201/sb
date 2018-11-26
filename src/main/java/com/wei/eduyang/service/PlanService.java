package com.wei.eduyang.service;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.domain.Tag;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.mapper.TagMapper;
import com.wei.eduyang.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import java.util.List;
import java.util.Map;

@Service
public class PlanService {

    @Autowired
    private PlanMapper planMapper;

    public ResultEntity searchQuery(JSONObject jsonParam){
        ResultEntity resultEntity = new ResultEntity();

        CommonUtil.convertPageSizeFromJson(jsonParam);
        Map paraMap = JSONObject.toJavaObject(jsonParam,Map.class);
        List<Plan> list = planMapper.searchQuery(paraMap);
        int count = planMapper.searchQueryCount(paraMap);
        JSONObject resultJson = new JSONObject();
        resultJson.put("list",list);
        resultJson.put("total",count);
        resultEntity.setData(resultJson);
        resultEntity.setReturnCode(ResultEntity.SUCCESS);

        return resultEntity;
    }

    public ResultEntity savePlan(JSONObject jsonParam){
        Plan plan = jsonParam.toJavaObject(Plan.class);
        if (plan.getId()!=0){
            //update
           planMapper.updatePlan(plan);
        }else{
            //insert
           planMapper.insertPlan(plan);
        }
        return new ResultEntity();
    }

}
