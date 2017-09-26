package com.wenku.user.web.interceptor;

import com.wenku.user.model.AdminAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sandy on 15/06/2017.
 */
public class AdminUserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String adminUsername =  (String)request.getSession().getAttribute(AdminAuth.ADMIN_SESSION_ATTR);
        if (StringUtils.isBlank(adminUsername)){
            response.sendRedirect("/admin/login");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
