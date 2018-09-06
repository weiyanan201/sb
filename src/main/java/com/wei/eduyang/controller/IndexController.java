package com.wei.eduyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.exception.SessionExpiredExcption;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String home(ModelMap map){
        map.put("username","weiyanan");
        map.put("role","admin");
        return "index";
    }


    @GetMapping("login")
    public String login (HttpServletRequest request,String userName,String password){
        System.out.println(userName+" : "+password);
        ResultEntity resultEntity = new ResultEntity();

        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    @GetMapping("checkLogin")
    public ResultEntity checkLogin (HttpServletRequest request, HttpServletResponse response){
        ResultEntity resultEntity = new ResultEntity();

        Subject subject = SecurityUtils.getSubject();
        logger.info("subject " + subject);
        String host = subject.getSession().getHost();
        logger.info("subject.host ： " + host);
        return resultEntity;
    }

    @RequestMapping("/test")
    public String test(int age) throws Exception {
        logger.info("hello world ",age);
        logger.error("hello error");
        createException();
        return "我是正常的";
    }

    private void createException() throws Exception {
        int i = 1/0;
        System.out.println(i);
    }
}
