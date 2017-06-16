package com.wenku.web.interceptor;

import com.sun.tools.doclint.Env;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * 系统变量拦截器，在页面处理前增加系统变量（系统时间,上下文）
 */
public class SystemVariableInterceptor extends HandlerInterceptorAdapter {

    private Environment env;

    private String appMode;

    public SystemVariableInterceptor(Environment env) {
        this.env = env;
        if (env!=null){
            this.appMode = env.getProperty("app.mode");
        }
    }

    /**
     * Add some system variables.
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView mv) throws Exception {
        if (mv == null) {
            return;
        }
        mv.addObject("ctx", request.getContextPath());
        Date serverTime = new Date();
        long serverTimestamp = serverTime.getTime();
        mv.addObject("server_timestamp", serverTimestamp);
        mv.addObject("build_time", serverTimestamp);
        mv.addObject("mode",this.appMode);
        //System.out.println("---------------- SystemVariable intercepted, app mode: " + env.getProperty("app.mode") + "--------------");
    }

}