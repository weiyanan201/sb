package com.wei.eduyang.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义sessionId获取
 */
public class MySessionManager extends DefaultWebSessionManager {

    private static Logger logger = LoggerFactory.getLogger(MySessionManager.class);

    public MySessionManager() {
        super();
    }
}

