package com.ems.config.filter;


import com.ems.common.constant.SecurityConstants;
import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.JwtUtil;
import com.ems.common.utils.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 13:15
 **/
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    /**
    * Comentario en espanol
    * @Param: [request, response, filterChain]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("Ruta solicitada: " + request.getRequestURI());
            // Comentario en espanol
            String token = this.getTokenFromHttpServletRequest(request);
            // Comentario en espanol
            // Comentario en espanol
            if (StringUtil.isBlank(token) || token.length() < 150){
                filterChain.doFilter(request, response);
                return;
            }
            // Comentario en espanol
            if (JwtUtil.verifyToken(token)){
                // Comentario en espanol
                Authentication authentication = JwtUtil.getAuthentication(token);
                // Comentario en espanol
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Comentario en espanol
                if (request.getRequestURI().startsWith("/api/")) {
                    // Comentario en espanol
                    String newRequestURI = request.getRequestURI().substring(4);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(newRequestURI);
                    dispatcher.forward(request, response);
                } else {
                    // Comentario en espanol
                    filterChain.doFilter(request, response);
                }
            }
        } catch (BadRequestException e) {
            // Comentario en espanol
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
    * Comentario en espanol
    * @Param: [request]
    * @return: java.lang.String
    * @Author: starao
    * @Date: 2021/11/27
    */
    private String getTokenFromHttpServletRequest(HttpServletRequest request){
        // Comentario en espanol
        String authorization = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (StringUtils.isNotBlank(authorization) && authorization.startsWith(SecurityConstants.TOKEN_PREFIX)){
            return authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
        }
        return null;
    }
}
