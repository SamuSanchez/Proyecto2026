package com.ems.config.security;

import com.ems.common.exception.BadRequestException;
import com.ems.system.entity.dto.JwtUserDto;
import com.ems.system.entity.dto.UserDto;
import com.ems.system.service.SysRoleService;
import com.ems.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 13:54
 **/
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService userService;

    private final SysRoleService roleService;

    /**
    * Comentario en espanol
    * @Param: [s]
    * @return: org.springframework.security.core.userdetails.UserDetails
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean searchDb = true;
        JwtUserDto jwtUserDto = null;
        if (searchDb) {
            UserDto user;
            try {
                user = userService.loadByName(username);
            } catch (BadRequestException e) {
                throw new UsernameNotFoundException("Usuario o contrasena incorrectos", e);
            }
            if (user == null) {
                throw new UsernameNotFoundException("Usuario o contrasena incorrectos");
            } else {
                if (!user.getEnabled()) {
                    throw new BadRequestException("La cuenta no esta activada");
                }
                jwtUserDto = new JwtUserDto(
                        user,
                        roleService.getRolesByUser(user.getId())
                );
            }
        }
        return jwtUserDto;
    }
}
