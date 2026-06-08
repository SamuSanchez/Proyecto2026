package com.ems.system.controller;

import com.ems.common.constant.CaptchaConstants;
import com.ems.common.constant.SecurityConstants;
import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.JwtUtil;
import com.ems.common.utils.ResultUtil;
import com.ems.common.utils.StringUtil;
import com.ems.config.config.CacheConfig;
import com.ems.config.security.JwtUser;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.SysRole;
import com.ems.system.entity.SysUser;
import com.ems.system.entity.dto.UserDto;
import com.ems.system.service.SysRoleService;
import com.ems.system.service.SysUserService;
import com.pig4cloud.captcha.ArithmeticCaptcha;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 14:08
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController extends ResultUtil {

    private final SysUserService userService;

    private final SysRoleService roleService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final CacheConfig cacheConfig;

    @Log("Inicio de sesion de usuario")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDto userDto, HttpServletRequest request){
        try {
            // Comentario en espanol
            if (StringUtil.isBlank(userDto.getCode()) || !checkCode(userDto.getUuid(), userDto.getCode())){
                return fail("Codigo de verificacion incorrecto");
            }
            // Comentario en espanol
            SysUser user = userService.findByName(userDto.getUsername());
            if (user == null){
                return fail("Usuario o contrasena incorrectos");
            }
            // Comentario en espanol
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            // Comentario en espanol
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Comentario en espanol
            List<String> roles = getRolesByUserId(user.getId());
            // Comentario en espanol
            String token = JwtUtil.generateToken(user.getUsername(), roles, false);
            // Comentario en espanol
            String refreshToken = JwtUtil.getRefreshToken(user.getUsername());

            // Comentario en espanol
            userDto.setEmail(user.getEmail());
            userDto.setNickName(user.getNickName());
            userDto.setRoles(roles);
            // Comentario en espanol
            userDto.setPassword("******");

            return success(new JwtUser(token, refreshToken, userDto));
        } catch (BadRequestException e) {
            e.printStackTrace();
            return fail(e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

    /**
    * @Description: Registro de usuario
    * @Param: [userDto]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Registro de usuario")
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto){
        try {
            userService.editUser(userDto);
            return success("Registro exitoso");
        } catch (BadRequestException e) {
            e.printStackTrace();
            return fail(e.getMsg());
        }
    }
    
    /**
    * Comentario en espanol
    * @Param: [request]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2022/10/5
    */
    @PutMapping("/refresh")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request){
        try {
            // Comentario en espanol
            String refreshToken = request.getHeader(SecurityConstants.TOKEN_HEADER);
            // Comentario en espanol
            if (StringUtil.isNotBlank(refreshToken)){
                // Comentario en espanol
                refreshToken = refreshToken.replaceFirst(SecurityConstants.TOKEN_PREFIX, "");
                // Comentario en espanol
                Claims claims = JwtUtil.getRefreshTokenBody(refreshToken);
                System.out.println(claims.getSubject());
                // Comentario en espanol
                if (claims.get("exp", Long.class) > 0){
                    // Comentario en espanol
                    SysUser user = userService.findByName(claims.getSubject());
                    // Comentario en espanol
                    List<String> roles = getRolesByUserId(user.getId());
                    // Comentario en espanol
                    String token = JwtUtil.generateToken(user.getUsername(), roles, false);
                    return success(token);
                }
            }
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
        return fail("Vuelve a iniciar sesion");
    }

    /**
     * Comentario en espanol
     * @Param: []
     * @return: org.springframework.http.ResponseEntity<java.lang.Object>
     * @Author: starao
     * @Date: 2022/1/18
     */
    @GetMapping("/code")
    public ResponseEntity<Object> getVerifyCode(){
        try {
            // Comentario en espanol
            ArithmeticCaptcha captcha = new ArithmeticCaptcha(CaptchaConstants.width, CaptchaConstants.height);
            // Comentario en espanol
            captcha.setLen(2);
            // Comentario en espanol
            captcha.supportAlgorithmSign(5);
            // Evita glifos no soportados en algunas fuentes (p. ej. "÷" como cuadrado).
            captcha.setArithmeticString(captcha.getArithmeticString().replace("x", "*").replace("÷", "/"));
            // Comentario en espanol
            String captchaValue = captcha.text();
            // Comentario en espanol
            String uuid = UUID.randomUUID().toString().replace("-", "");
            cacheConfig.put(uuid, captchaValue, 1);
            // Comentario en espanol
            Map<String, Object> imgResult = new HashMap<>(2) {{
                put("img", captcha.toBase64());
                put("uuid", uuid);
            }};
            return ResponseEntity.ok(imgResult);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

    /**
    * Comentario en espanol
    * @Param: [userId]
    * @return: java.util.List<java.lang.String>
    * @Author: starao
    * @Date: 2022/10/5
    */
    private List<String> getRolesByUserId(Long userId){
        // Comentario en espanol
        List<SysRole> sysRoles = roleService.getRoleByUserId(userId);
        // Comentario en espanol
        List<String> roles = new ArrayList<>();
        sysRoles.forEach( sysRole -> {
            roles.add(sysRole.getRoleCode());
        });
        return roles;
    }

    /**
     * Comentario en espanol
     * @Param: [uuid, code]
     * @return: boolean
     * @Author: starao
     * @Date: 2023/7/9
     */
    private boolean checkCode(String uuid, String code){
        boolean b = false;
        if (cacheConfig.get(uuid) != null && cacheConfig.get(uuid).equals(code)){
            b = true;
            cacheConfig.invalidate(uuid);
        }
        return b;
    }
}
