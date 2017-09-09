package com.wenku.security;

import com.wenku.security.filter.UserOnLineFilter;
import com.wenku.user.model.User;
import com.wenku.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sandy on 29/08/2017.
 */
@Service
public class Guard {

    @Resource
    private UserService userService;

    public User getLoginUser(HttpServletRequest request){
        Object uid = getUID(request);
        if (uid!=null){
            Long userId = Long.parseLong(uid.toString());
            return userService.findOne(userId);
        }
        return null;
    }

    public boolean isLogined(HttpServletRequest request){
        return getUID(request)!=null;
    }

    public Long getUID(HttpServletRequest request){
        return (Long)request.getSession().getAttribute(UserOnLineFilter.UID_ATTRIBUTE);
    }

    public void auth(HttpServletRequest request, Long uid){
        request.getSession().setAttribute(UserOnLineFilter.UID_ATTRIBUTE,uid);
    }

    public void unAuth(HttpServletRequest request){
        request.getSession().removeAttribute(UserOnLineFilter.UID_ATTRIBUTE);
    }


}
