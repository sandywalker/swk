package com.wenku.config;

import com.wenku.security.filter.UserOnLineFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

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
        FilterRegistration userOnlineFilter = servletContext.addFilter("userOnlineFilter", new UserOnLineFilter("/login","/user/**","/admin/**"));

        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    Welcome to Sowenku ! \r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());


    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true);

        UserOnLineFilter userOnLineFilter = new UserOnLineFilter("/login","/user/**","/admin/**");
        return new Filter[]{characterEncodingFilter,userOnLineFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        boolean done = registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); // -> true
        if(!done) throw new RuntimeException();
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
