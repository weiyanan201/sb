package com.wei.eduyang.config;

import com.alibaba.fastjson.JSONObject;
import com.wei.eduyang.bean.ResultEntity;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UploadMaxSizeInterceptor extends HandlerInterceptorAdapter {

    @Value("${app.uploadMaxSize}")
    private long maxSize ;

    @Value(("${app.uploadMaxSizeTip}"))
    private String tip ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request!=null && ServletFileUpload.isMultipartContent(request)) {
            System.out.println(System.currentTimeMillis()+"UploadMaxSizeInterceptor");
            ServletRequestContext ctx = new ServletRequestContext(request);
            long requestSize = ctx.contentLength();
            if (requestSize > maxSize) {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setHeader("Content-type", "application/json;charset=UTF-8");
                ResultEntity resultEntity = new ResultEntity(ResultEntity.ERROR,"上传文件超过 "+tip,null);
                response.getWriter().print(JSONObject.toJSON(resultEntity));
                return false;
            }
        }
        return true;
    }
}
