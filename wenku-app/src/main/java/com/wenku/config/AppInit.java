package com.wenku.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by sandy on 02/06/2017.
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        return super.registerServletFilter(servletContext, filter);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    Welcome to Sowenku ! \r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CharacterEncodingFilter("UTF-8",true)};
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {AppConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {MVCConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
