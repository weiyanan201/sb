package com.wei.eduyang.controller;

import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.Test;
import com.wei.eduyang.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
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


    @GetMapping("login")
    public ResultEntity login (HttpServletRequest request, HttpServletResponse response){
        ResultEntity resultEntity = new ResultEntity();
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("userName"));
        session.setAttribute("userName","weiyanan");

        resultEntity.setReturnCode(ResultEntity.SUCCESS);
        return resultEntity;
    }
}
