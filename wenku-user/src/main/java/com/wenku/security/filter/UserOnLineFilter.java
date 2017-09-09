package com.wenku.security.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sandy on 10/08/2017.
 */
public class UserOnLineFilter implements Filter {

    private FilterConfig config;
    private String redirectTo;
    private String excludes;
    private String mappings;

    public static final  String UID_ATTRIBUTE = "webku.uid";

    public UserOnLineFilter(String redirectTo, String mappings,String excludes) {
        this.redirectTo = redirectTo;
        this.excludes = excludes;
        this.mappings = mappings;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        if (PatternMatchUtils.simpleMatch(this.excludes,request.getRequestURI())){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (!PatternMatchUtils.simpleMatch(this.mappings,request.getRequestURI())){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if (request.getSession().getAttribute(UID_ATTRIBUTE)!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            String redirect = StringUtils.isBlank(redirectTo) ? "/" : redirectTo;
            response.sendRedirect(redirect);
        }
    }

    @Override
    public void destroy() {
        this.config = null;
    }
}
