package com.wei.eduyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String home(ModelMap map){
        return "index";
    }


    @PostMapping("login")
    @ResponseBody
    public ResultEntity login (HttpServletRequest request,String userName,String password){
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
        return resultEntity;
    }

    @GetMapping("checkLogin")
    @ResponseBody
    public ResultEntity checkLogin (){
        ResultEntity resultEntity = new ResultEntity();
        Subject subject = SecurityUtils.getSubject();
        resultEntity.setReturnCode(ResultEntity.SUCCESS);
        resultEntity.setData(subject.isAuthenticated());
        return resultEntity;
    }

    @RequestMapping("/test")
    public String test(int age) throws Exception {
        logger.info("hello world ",age);
        logger.error("hello error");
        createException();
        return "我是正常的";
    }

    @ResponseBody
    @RequestMapping("/api/test")
    public String testAuth() {
        return "权限通过";
    }


    private void createException() throws Exception {
        int i = 1/0;
        System.out.println(i);
    }
}
