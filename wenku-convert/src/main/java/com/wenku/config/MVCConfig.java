package com.wenku.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by sandy on 02/06/2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.wenku",includeFilters = {@ComponentScan.Filter(Controller.class)})
@PropertySource({"classpath:app.properties","classpath:app.local.properties"})
public class MVCConfig extends WebMvcConfigurerAdapter {


    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }




}
