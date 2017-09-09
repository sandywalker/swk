package com.wenku.config;

import com.wenku.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;

/**
 * Created by sandy on 02/06/2017.
 */

@Configuration
@EnableScheduling
@ComponentScan(value = "com.wenku",excludeFilters= {@ComponentScan.Filter(Controller.class)})
@PropertySource({"classpath:app.properties","classpath:app.local.properties"})
@ImportResource("classpath:data-config.xml")
@EnableRedisHttpSession
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean(name = "validator")
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

    @Bean
    @Lazy(value = false)
    public SpringContextUtils springContextUtils(){
        return new SpringContextUtils();
    }

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean(name = "mailSender")
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setProtocol(env.getProperty("mail.protocol"));
        mailSender.setPort(env.getProperty("mail.port",Integer.class));
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth","true");
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

}
