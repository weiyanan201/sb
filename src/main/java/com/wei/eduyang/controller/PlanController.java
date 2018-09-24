package com.wei.eduyang.controller;

import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plan")

public class PlanController {

    @Autowired
    private PlanService planService;


    @GetMapping("getTag")
    public ResultEntity getTag() {

        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturnCode(ResultEntity.SUCCESS);
        resultEntity.setData(planService.getAllTags());

        return resultEntity;
    }
}
