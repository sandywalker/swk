package com.wenku.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by sandy on 02/06/2017.
 */

@Configuration
@EnableScheduling
@ComponentScan(value = "com.wenku",excludeFilters= {@ComponentScan.Filter(Controller.class)})
@PropertySource({"classpath:app.properties","classpath:app.local.properties"})
@ImportResource("classpath:data-config.xml")
public class AppConfig {


    @Bean(name = "validator")
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

}
