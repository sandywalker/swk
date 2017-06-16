package com.wenku.config;

import com.wenku.user.web.interceptor.AdminUserInterceptor;
import com.wenku.web.interceptor.SystemVariableInterceptor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;
import java.util.List;


/**
 * Created by sandy on 02/06/2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.wenku",includeFilters = {@ComponentScan.Filter(Controller.class)})
@PropertySource({"classpath:app.properties","classpath:app.local.properties"})
public class MVCConfig extends WebMvcConfigurerAdapter {


    @Autowired
    private Environment env;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name="multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(FileUtils.ONE_GB*4);//4GB
        return resolver;
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0, 20));
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }


    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SystemVariableInterceptor(env))
                .addPathPatterns("/**").excludePathPatterns("/resources/**");
        registry.addInterceptor(new AdminUserInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login");
        super.addInterceptors(registry);
    }


}
