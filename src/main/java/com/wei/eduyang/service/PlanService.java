package com.wei.eduyang.service;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.domain.Tag;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.mapper.TagMapper;
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

        Map paraMap = JSONObject.toJavaObject(jsonParam,Map.class);
        List<Plan> list = planMapper.searchQuery(paraMap);

        resultEntity.setData(list);
        resultEntity.setReturnCode(ResultEntity.SUCCESS);

        return resultEntity;
    }

    public ResultEntity savePlan(JSONObject jsonParam){
        ResultEntity resultEntity = new ResultEntity();
        Plan plan = jsonParam.toJavaObject(Plan.class);
        if (plan.getId()!=0){
            //update
            planMapper.updatePlan(plan);
        }else{
            //insert
            planMapper.insertPlan(plan);
        }


        resultEntity.setReturnCode(ResultEntity.SUCCESS);
        return resultEntity;
    }

}
