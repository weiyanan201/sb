package com.wei.eduyang.controller;

import com.wei.eduyang.domain.Test;
import com.wei.eduyang.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/")
    public String home(ModelMap map){
        map.put("username","weiyanan");
        map.put("role","admin");
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
        return "index";
    }
}
