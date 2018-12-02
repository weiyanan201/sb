package com.wei.eduyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import com.wei.eduyang.domain.User;
import com.wei.eduyang.enums.UserType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 首页
     */
    @RequestMapping("/")
    public String home(HttpServletResponse response) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "index";
        }else{
            return "redirect:/login";
        }
    }

    /**
     * 登录页面
     */
    @RequestMapping("/login")
    public String login(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "redirect:/";
        }else{
            return "login.html";
        }
    }

    /**
     * 详情页面
     */
    @RequestMapping("/detail")
    public String detailPage(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "detail";
        }else{
            return "redirect:/login.html";
        }
    }

    @RequestMapping(value = "error2")
    public String errorPage(){
        return "error";
    }

    /**
     * 登录验证
     */
    @PostMapping("checkLogin")
    @ResponseBody
    public ResultEntity checkLogin (HttpServletRequest request,String userName,String password){
        ResultEntity resultEntity = new ResultEntity();
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            throw e;
        }
        resultEntity.setData(jsonObject);
        return resultEntity;
    }

    @GetMapping("/api/getUserInfo")
    @ResponseBody
    public ResultEntity getUserInfo (){
        ResultEntity resultEntity = new ResultEntity();
        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();
        UserType.getName(user.getUserType());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role",UserType.getName(user.getUserType()));
        jsonObject.put("userName",user.getUserName());

        resultEntity.setData(jsonObject);
        resultEntity.setReturnCode(ResultEntity.SUCCESS);
        return resultEntity;
    }

    @GetMapping("/login2")
    @ResponseBody
    @RequiresRoles(value = {UserType.ADMIN_STR})
    public String test(HttpServletResponse response) {
        return "aaaa";
    }

}
