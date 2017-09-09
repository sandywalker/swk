package com.wenku.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring上下文工具类，用于获取bean
 * <p>注意：要是该类起作用，必须将此类配置到spring 的 application-context.xml中，并且设置 延迟加载为false;</p>
 * @author SIND
 * @since 2013-3-22
 */
public class SpringContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 从上下文获取bean的通用方法
     * @param name bean 名称
     * @return
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }


    /**
     * 从上下文获取bean的通用方法,使用范型，避免转型
     * @param name bean 名称
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }


    /**
     * 根据类型从上下文获取 bean ，前提是该类型的实例在上下文中是唯一的，否则宝座
     * @param clazz bean 类
     * @param <T> 返回类型
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

}
