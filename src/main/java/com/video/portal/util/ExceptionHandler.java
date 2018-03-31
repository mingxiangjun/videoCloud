package com.video.portal.util;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Copyright：DR Technology (Beijing) CO.,LTD
 * Author: DingRan
 * Date: 2018/3/29
 * Description:
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {

        Map<String, Object> map = new HashMap<String, Object>();
        List<String> messages = new ArrayList<String>();
        map.put("ok", false);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (e instanceof HttpSessionRequiredException) {
            messages.add("您还没有登录或者离开页面的时间过长，请登录系统或刷新页面！");
            pw.write("<script type=\"text/javascript\">window.location.href = \"/\";</script>");
            pw.flush();
            map.put("isLogin",false);
        } else {
            messages.add(e.getMessage());
            map.put("isLogin",true);
        }
        map.put("created", new Date());
        map.put("data", messages);
        return null;
    }
}
