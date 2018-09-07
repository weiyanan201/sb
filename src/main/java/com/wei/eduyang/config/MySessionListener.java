package com.wei.eduyang.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySessionListener implements SessionListener {

    private static Logger logger = LoggerFactory.getLogger(MySessionListener.class);

    @Override
    public void onStart(Session session) {
        logger.info(session.getId() + " session start ");
    }

    @Override
    public void onStop(Session session) {
        logger.info(session.getId() + " session onStop ");
    }

    @Override
    public void onExpiration(Session session)  {
        logger.info(session.getId() + " session onExpiration ");
    }
}
