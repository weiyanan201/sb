package com.wei.eduyang.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionExpiredFilter extends AccessControlFilter {

    private static Logger logger = LoggerFactory.getLogger(SessionExpiredFilter.class);

    private SessionManager sessionManager ;

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.info("isAccessAllowed");
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated()){
            return true;
        }else {
            //ajax 请求设置401
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
                res.setHeader("Location","/login");
            }else{
                //非ajax请求直接跳转
//                request.getRequestDispatcher("/").forward(request, response);
                res.sendRedirect("/");
            }
            return false;
        }
    }
}
