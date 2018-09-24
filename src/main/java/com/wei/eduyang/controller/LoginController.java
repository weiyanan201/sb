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
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String home(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "index";
        }else{
            return "redirect:/login";
        }
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String page1(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "redirect:/";
        }else{
            return "login";
        }
    }

    /**
     * 登录验证
     * @param request
     * @param userName
     * @param password
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public ResultEntity login (HttpServletRequest request,String userName,String password){
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

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9//.//@_//-~#]+");
        String sql = "aaa.aa";
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()){
            System.out.println(matcher.group());
        }
        System.out.println("end");
    }
}
