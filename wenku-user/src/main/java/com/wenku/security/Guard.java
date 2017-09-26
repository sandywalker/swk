package com.wenku.security;

import com.wenku.security.filter.UserOnLineFilter;
import com.wenku.user.model.User;
import com.wenku.user.service.UserService;
import com.wenku.util.MVCUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sandy on 29/08/2017.
 */
@Service
public class Guard {

    @Resource
    private UserService userService;

    public static final  String UID_ATTRIBUTE = "webku.uid";

    public User getLoginUser(HttpServletRequest request){
        Object uid = getUID(request);
        if (uid!=null){
            Long userId = Long.parseLong(uid.toString());
            return userService.findOne(userId);
        }
        return null;
    }

    public static boolean authByCookie(HttpServletRequest request,HttpServletResponse response){
        String uid = MVCUtils.getCookie(request,UID_ATTRIBUTE);
        if (StringUtils.isNotBlank(uid)){
            Guard.auth(request,response,Long.parseLong(uid),false);
            return true;
        }
        return false;
    }

    public static  boolean isLogined(HttpServletRequest request){
        return getUID(request)!=null;
    }

    public static  Long getUID(HttpServletRequest request){
        return (Long)request.getSession().getAttribute(UID_ATTRIBUTE);
    }

    public static void auth(HttpServletRequest request, HttpServletResponse response,Long uid, boolean remember){
        request.getSession().setAttribute(UID_ATTRIBUTE,uid);
        if (remember){
            MVCUtils.setCookie(response,UID_ATTRIBUTE,uid.toString(),60*60*24*30);
        }
    }

    public static  void unAuth(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute(UID_ATTRIBUTE);
        String uid = MVCUtils.getCookie(request,UID_ATTRIBUTE);
        if (StringUtils.isNotBlank(uid)){
            MVCUtils.setCookie(response,UID_ATTRIBUTE,"",0);
        }
    }


}
