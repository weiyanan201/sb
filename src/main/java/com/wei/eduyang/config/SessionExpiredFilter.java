package com.wei.eduyang.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
        logger.info("SessionExpiredFilter.onAccessDenied : " + subject);
        return false;
    }
}
