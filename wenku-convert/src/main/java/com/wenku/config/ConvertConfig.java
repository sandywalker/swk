package com.wenku.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

/**
 * Created by sandy on 02/06/2017.
 */

@Configuration
@EnableScheduling
@ComponentScan(value = "com.wenku",excludeFilters= {@ComponentScan.Filter(Controller.class)})
@PropertySource({"classpath:app.properties","classpath:app.local.properties"})
@ImportResource("classpath:data-config.xml")
public class ConvertConfig {

    @Autowired
    private Environment env;

}
