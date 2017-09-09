package com.wenku.user.util;

import com.wenku.util.DigestUtils;

import java.io.IOException;

/**
 * Created by sandy on 08/08/2017.
 */
public class UserUtils {

    public static String genActiveCode(String value){
        try {
            return DigestUtils.md5(value + System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }
}
