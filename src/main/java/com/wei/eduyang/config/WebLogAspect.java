package com.wei.eduyang.config;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.domain.User;
import com.wei.eduyang.service.PlanService;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * com.wei.eduyang.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容

        JSONObject outObject = new JSONObject();
        JSONObject argsObject = new JSONObject();
        try{
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            outObject.put("method",methodSignature.getDeclaringTypeName() + "." + methodSignature.getName());
            outObject.put("args",argsObject);

            Object[] args = joinPoint.getArgs();
            String[] parameterNames = methodSignature.getParameterNames();
            for (int i=0;i<args.length;i++){
                Object object = args[i];
                if (object instanceof Subject){
                    Subject s = (Subject) object;
                    User user  = (User) s.getPrincipal();
                    if (user!=null){
                        argsObject.put("userName",user.getUserName());
                    }
                }else if (object instanceof HttpServletRequest){
                    argsObject.put(parameterNames[i],"HttpServletRequest");
                }else if(object instanceof HttpServletResponse){
                    argsObject.put(parameterNames[i],"HttpServletResponse");
                }else{
                    argsObject.put(parameterNames[i],object);
                }
            }
            logger.info(outObject.toJSONString());
        }catch (Exception e){
            logger.error("webLog doBefore error : ",e );
        }

    }
}
