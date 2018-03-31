package com.video.portal.interceptor;

import com.video.portal.bean.SessionItem;
import com.video.portal.comm.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class SecurityInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
    private static final String LOGIN_URL = "/login.html";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        HttpSession session = req.getSession(true);
        SessionItem si = (SessionItem) session.getAttribute(Constants.SESSION_ID);
/*        // 从session 里面获取用户名的信息
        Object obj = session.getAttribute(Constants.SESSION_ID);
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
        if (obj == null || "".equals(obj.toString())) {
            res.sendRedirect(LOGIN_URL);
        }*/
        if(req.getRequestURI().contains("verifyCode")){
            return true;
        }
        if(si==null){
            res.sendRedirect(LOGIN_URL);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {
    }

}
