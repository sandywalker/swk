package com.wenku.util;

import com.google.common.reflect.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandy on 14/03/2017.
 */
public class MVCUtils {

    public static Logger logger = LoggerFactory.getLogger(MVCUtils.class);

    /**
     * 初始化自定义的属性绑定
     * @param b
     */
    public static void initBinder(WebDataBinder b){
        b.registerCustomEditor( Date.class, new CustomDateEditor( new SimpleDateFormat(DateTimeFormats.DEFAULT_DATE_FORMAT), true ));
        b.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    /**
     * 获取 Form 请求参数中针对某个 class 类，非 Form 字段的字段数组
     * @param clazz 类型信息
     * @return
     */
    public static String[] getIgnoredFields(Class clazz,HttpServletRequest request){
        Map<String,Field>  fieldMap = new HashMap<>();
        ReflectionUtils.doWithFields(clazz, field -> fieldMap.put(field.getName(),field));
        Map<String,String[]> params = request.getParameterMap();
        for(String paramName:params.keySet()){
            if (fieldMap.containsKey(paramName)){
                fieldMap.remove(paramName);
            }
        }
        int length = fieldMap.size();
        if (length == 0){
            return null;
        }
        String[] fieldNames = new String[length];
        int i=0;
        for(String name:fieldMap.keySet()){
            fieldNames[i] = name;
            i++;
        }
        return fieldNames;
    }

    public static String[] getIgnoredFields(Class clazz){
        return  getIgnoredFields(clazz,getCurrentRequest());
    }

    /**
     * 获取当前的web请求
     * 注意：在 Ajax 提交带文件上传带时候获取不到参数
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getSession() {
        return getCurrentRequest().getSession();
    }

    public static String getWebRoot(){
        return getCurrentRequest().getSession().getServletContext().getRealPath("/");
    }

    public static String getHostAndContextPath(){
        HttpServletRequest request = getCurrentRequest();
        if (request!=null){
            return request.getRequestURL().toString().replace(request.getServletPath(),"");
        }
        return null;
    }


    /**
     * 设置 Cookie（生成时间为1天）
     * @param name 名称
     * @param value 值
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, 60*60*24);
    }

    /**
     * 设置 Cookie
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path) {
        setCookie(response, name, value, path, 60*60*24);
    }

    /**
     * 设置 Cookie
     * @param name 名称
     * @param value 值
     * @param maxAge 生存时间（单位秒）
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        setCookie(response, name, value, "/", maxAge);
    }

    /**
     * 设置 Cookie
     * @param name 名称
     * @param value 值
     * @param maxAge 生存时间（单位秒）
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        try {
            cookie.setValue(URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addCookie(cookie);
    }

    /**
     * 获得指定Cookie的值
     * @param name 名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, null, name, false);
    }
    /**
     * 获得指定Cookie的值，并删除。
     * @param name 名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        return getCookie(request, response, name, true);
    }
    /**
     * 获得指定Cookie的值
     * @param request 请求对象
     * @param response 响应对象
     * @param name 名字
     * @param isRemove 是否移除
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (isRemove) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        return value;
    }

}
