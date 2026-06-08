package com.ems.common.utils;

import com.ems.common.constant.SecurityConstants;
import com.ems.common.exception.BadRequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.CollectionUtils;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 12:47
 **/
@Slf4j
public class JwtUtil {

    private static final String secretKey = SecurityConstants.JWT_SECRET_KEY;

    private static final String refreshKey = SecurityConstants.JWT_REFRESH_KEY;

    private JwtUtil(){
        throw new IllegalStateException("No esta permitido crear esta instancia");
    }

    /**
     * Comentario en espanol
     * @return
     */
    private static SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Comentario en espanol
     * @return
     */
    private static SecretKey getRefreshKey() {
        byte[] keyBytes = refreshKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
    * Comentario en espanol
    * @Param: [userName, roles, isRemember]
    * @return: java.lang.String
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static String generateToken(String userName, List<String> roles, Boolean isRemember){
        try {
            // Comentario en espanol
            long expirationTime = isRemember ? SecurityConstants.TOKEN_EXPIRATION_REMEMBER_TIME : SecurityConstants.TOKEN_EXPIRATION_TIME;

            // Comentario en espanol
            return Jwts.builder()
                    // Comentario en espanol
                    .signWith(getSigningKey())
                    // Comentario en espanol
                    .subject(userName)
                    // Comentario en espanol
                    .claim(SecurityConstants.TOKEN_ROLE_CLAIM, roles)
                    // Comentario en espanol
                    .issuer(SecurityConstants.TOKEN_ISSUER)
                    // Comentario en espanol
                    .issuedAt(new Date())
                    .audience().add(SecurityConstants.TOKEN_AUDIENCE).and()
                    // Comentario en espanol
                    .expiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                    .compact();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    /**
    * Comentario en espanol
    * @Param: [userName]
    * @return: java.lang.String
    * @Author: starao
    * @Date: 2022/10/5
    */
    public static String getRefreshToken(String userName){
        return Jwts.builder()
                .signWith(getRefreshKey())
                .subject(userName)
                .expiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_REMEMBER_TIME * 1000))
                .compact();
    }

    /**
    * Comentario en espanol
    * @Param: [token]
    * @return: boolean
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static boolean verifyToken(String token){
        try {
            getTokenBody(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("token expirado: {} failed: {}", token, e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("token no soportado: {} failed: {}", token, e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("formato de token invalido: {} failed: {}", token, e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (SignatureException e) {
            log.warn("firma de token invalida: {} failed: {}", token, e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("el token no puede estar vacio: {} failed: {}", token, e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }

    /**
    * Comentario en espanol
    * @Param: [token]
    * @return: org.springframework.security.core.Authentication
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static Authentication getAuthentication(String token) {
        Claims claims;
        try {
            claims = getTokenBody(token);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            claims = e.getClaims();
        }
        // Comentario en espanol
        List<String> roles = (List<String>) claims.get(SecurityConstants.TOKEN_ROLE_CLAIM);
        List<SimpleGrantedAuthority> authorities =
                CollectionUtils.isEmpty(roles) ? Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) :
                        roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        // Comentario en espanol
        User principal = new User(claims.getSubject(), "******", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
    * Comentario en espanol
    * @Param: [token]
    * @return: io.jsonwebtoken.Claims
    * @Author: starao
    * @Date: 2021/11/27
    */
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    /**
    * Comentario en espanol
    * @Param: [refreshToken]
    * @return: io.jsonwebtoken.Claims
    * @Author: starao
    * @Date: 2022/10/5
    */
    public static Claims getRefreshTokenBody(String refreshToken){
        return Jwts.parser()
                .verifyWith(getRefreshKey())
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload();
    }
}
