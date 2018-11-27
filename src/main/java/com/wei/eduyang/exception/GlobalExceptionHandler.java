package com.wei.eduyang.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wei.eduyang.bean.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 所有异常报错
     * @param exception
     * @return
     */
    @ExceptionHandler(value=Exception.class)
    public ResultEntity allExceptionHandler(HttpServletRequest request,HttpServletResponse response,Exception exception) {
        logger.error("系统异常：",exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturnCode(ResultEntity.ERROR);
        resultEntity.setReturnMessage("服务器异常，请稍后再试！");
        return resultEntity;
    }

    /**
     * 捕获 SessionExpiredExcption 超时异常
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=SessionExpiredExcption.class)
    public ResultEntity sessionExpiredExceptionHandler(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("Location","/");
        }
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setReturnCode(ResultEntity.ERROR);
        resultEntity.setReturnMessage("登录已失效");
        return resultEntity;
    }

//    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
//    public String test(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        System.out.println("xxx");
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//
//
//        System.out.println("######################### handleException capture this exception");
//        return "错误信息";
//    }



}