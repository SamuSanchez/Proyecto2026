package com.ems.common.constant;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 12:48
 **/
public class CommonConstants {

    private CommonConstants(){
        throw new IllegalStateException("No se puede crear una clase de constantes estaticas");
    }

    /**
     * Comentario en espanol
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * Comentario en espanol
     */
    public static final String DEFAULT_PASSWORD = "111111";
}
