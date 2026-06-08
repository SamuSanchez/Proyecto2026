package com.ems.system.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.common.constant.CommonConstants;
import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.SecurityUtil;
import com.ems.common.utils.StringUtil;
import com.ems.system.entity.SysUser;
import com.ems.system.entity.dto.QueryDto;
import com.ems.system.entity.dto.UserDto;
import com.ems.system.mapper.SysUserMapper;
import com.ems.system.service.SysRoleUserService;
import com.ems.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 14:44
 **/
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;

    private final PasswordEncoder passwordEncoder;

    private final SysRoleUserService roleUserService;

    /**
     * @param userName
     * Comentario en espanol
     * @Param: [userName]
     * @return: com.ems.system.entity.SysUser
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public SysUser findByName(String userName) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, userName);
        return sysUserMapper.selectOne(wrapper);
    }

    /**
     * @param userDto
     * @Description: Editar usuario
     * @Param: [userDto]
     * @return: void
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public void editUser(UserDto userDto) {
        checkUser(userDto);
        SysUser user = new SysUser();
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setId(userDto.getId());
        user.setNickName(userDto.getNickName());
        if (userDto.getEnabled() != null){
            user.setEnabled(userDto.getEnabled());
        }
        if (user.getId() != null){
            sysUserMapper.updateById(user);
        } else {
            // Comentario en espanol
            user.setPassword(passwordEncoder.encode(CommonConstants.DEFAULT_PASSWORD));
            sysUserMapper.insert(user);
        }

        // Comentario en espanol
        if (!CollectionUtils.isEmpty(userDto.getRoleIds()) && user.getId() != null){
            roleUserService.edit(user.getId(), userDto.getRoleIds());
        }
    }

    /**
     * @param queryDto
     * @Description: Consultar lista de usuarios
     * @Param: [blurry]
     * @return: java.util.List<com.ems.system.entity.SysUser>
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public IPage<UserDto> queryUserTable(QueryDto queryDto) {
        Page<UserDto> page = new Page<>();
        page.setCurrent(queryDto.getCurrentPage());
        page.setSize(queryDto.getSize());
        return sysUserMapper.queryUserTable(page, queryDto.getBlurry());
    }

    /**
     * @param username
     * Comentario en espanol
     * @Param: [username]
     * @return: com.ems.system.entity.dto.UserDto
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public UserDto loadByName(String username) {
        return sysUserMapper.loadByName(username);
    }

    /**
     * @param id
     * @Description: Eliminar usuario
     * @Param: [id]
     * @return: void
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    @Transactional(rollbackFor = BadRequestException.class)
    public void delUser(Long id) {
        // Comentario en espanol
        roleUserService.deleteByUserId(id);
        // Comentario en espanol
        sysUserMapper.deleteById(id);
    }

    /**
     * @param sysUser
     * Comentario en espanol
     * @Param: [sysUser]
     * @return: void
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public void enabledUser(SysUser sysUser) {
        sysUserMapper.updateById(sysUser);
    }

    /**
     * @param jsonObject
     * @Description: Cambiar contrasena del usuario
     * @Param: [jsonObject]
     * @return: void
     * @Author: starao
     * @Date: 2022/10/6
     */
    @Override
    public void updatePassword(JSONObject jsonObject) {
        String password = jsonObject.getString("password");
        String newPassword = jsonObject.getString("newPassword");
        String confirmPassword = jsonObject.getString("confirmPassword");

        // En PostgreSQL evitamos mismatch de tipo en id leyendo por username del contexto.
        SysUser user = findByName(SecurityUtil.getCurrentUsername());
        if (user == null) {
            throw new BadRequestException("No se encontro el usuario actual");
        }
        String pwd = user.getPassword();
        // Comentario en espanol
        if (!passwordEncoder.matches(password, pwd)){
            throw new BadRequestException("La contrasena actual es incorrecta");
        }
        // Comentario en espanol
        if (!newPassword.equals(confirmPassword)){
            throw new BadRequestException("La nueva contrasena y la confirmacion no coinciden");
        }
        // Comentario en espanol
        user.setPassword(passwordEncoder.encode(newPassword));
        sysUserMapper.updateById(user);
    }

    /**
    * Comentario en espanol
    * @Param: [userDto]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    private void checkUser(UserDto userDto){
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();

        if (userDto.getId() != null){
            wrapper.ne(SysUser::getId, userDto.getId());
        }
        if (StringUtil.isNotBlank(userDto.getUsername()) && StringUtil.isNotBlank(userDto.getNickName())){
            wrapper.and(w -> w.eq(SysUser::getUsername, userDto.getUsername()).or().eq(SysUser::getNickName, userDto.getNickName()));
        }
        long count = sysUserMapper.selectCount(wrapper);
        if (count > 0){
            throw new BadRequestException("El usuario o nombre ya existe");
        }
    }
}
