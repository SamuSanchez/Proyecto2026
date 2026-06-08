package com.ems.common.constant;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 12:49
 **/
public class SecurityConstants {

    private SecurityConstants(){
        throw new IllegalStateException("No se puede crear una clase de constantes estaticas");
    }

    /**
     * Comentario en espanol
     */
    public static final String AUTH_LOGIN_URL = "/auth/login";

    /**
     * Comentario en espanol
     * Comentario en espanol
     */
    public static final String JWT_SECRET_KEY = "B?E(H+MbQeShVmYq3t6w9z$C&F)J@NcRfUjWnZr4u7x!A%D*G-KaPdSgVkYp3s5v";

    /**
     * Comentario en espanol
     */
    public static final String JWT_REFRESH_KEY = "q3t6w9z$C&F)H@McQfTjWnZr4u7x!A%D*G-KaNdRgUkXp2s5v8y/B?E(H+MbQeSh";

    /**
     * Comentario en espanol
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Comentario en espanol
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Comentario en espanol
     */
    public static final String TOKEN_TYPE = "JWT";

    /**
     * Comentario en espanol
     */
    public static final String TOKEN_ROLE_CLAIM = "role";

    /**
     * Comentario en espanol
     */
    public static final String TOKEN_ISSUER = "security";

    /**
     * Comentario en espanol
     */
    public static final String TOKEN_AUDIENCE = "security-all";

    /**
     * Comentario en espanol
     */
    public static final Long TOKEN_EXPIRATION_TIME = 60 * 60 * 2L;

    /**
     * Comentario en espanol
     */
    public static final Long TOKEN_EXPIRATION_REMEMBER_TIME = 60 * 60 * 24 * 7L;
}
