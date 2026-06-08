package com.ems.common.utils;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ems.common.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 13:00
 **/
public class SecurityUtil {

    /**
    * Comentario en espanol
    * @Param: []
    * @return: org.springframework.security.core.userdetails.UserDetails
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static UserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "La sesion actual ha expirado");
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
            return userDetailsService.loadUserByUsername(userDetails.getUsername());
        }
        throw new BadRequestException(HttpStatus.UNAUTHORIZED, "No se encontro la informacion de la sesion actual");
    }

    /**
    * Comentario en espanol
    * @Param: []
    * @return: java.lang.String
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "La sesion actual ha expirado");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    /**
    * Comentario en espanol
    * @Param: []
    * @return: java.lang.String
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static Long getCurrentUserId() {
        UserDetails userDetails = getCurrentUser();
        if (userDetails != null){
            return JSON.parseObject(JSONObject.toJSONString(userDetails)).getJSONObject("user").getLong("id");
        } else {
            return null;
        }
    }

    /**
    * Comentario en espanol
    * @Param: []
    * @return: java.util.List<java.lang.String>
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static List<String> getCurrentRoles(){
        UserDetails userDetails = getCurrentUser();
        List<String> list = new ArrayList<>();
        if (userDetails != null){
            userDetails.getAuthorities().forEach((item) -> {
                list.add(item.toString());
            });
        }
        return list;
    }
}
