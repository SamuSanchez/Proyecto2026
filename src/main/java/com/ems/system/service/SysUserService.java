package com.ems.system.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ems.system.entity.SysUser;
import com.ems.system.entity.dto.QueryDto;
import com.ems.system.entity.dto.UserDto;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a interface
 * @author: starao
 * @create: 2021-11-27 14:28
 **/
public interface SysUserService {

    /**
    * Comentario en espanol
    * @Param: [userName]
    * @return: com.ems.system.entity.SysUser
    * @Author: starao
    * @Date: 2021/11/27
    */
    SysUser findByName(String userName);

    /**
    * @Description: Editar usuario
    * @Param: [userDto]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void editUser(UserDto userDto);

    /**
    * @Description: Consultar lista de usuarios
    * @Param: [queryDto]
    * @return: java.util.List<com.ems.system.entity.SysUser>
    * @Author: starao
    * @Date: 2021/11/27
    */
    IPage<UserDto> queryUserTable(QueryDto queryDto);

    /**
    * Comentario en espanol
    * @Param: [username]
    * @return: com.ems.system.entity.dto.UserDto
    * @Author: starao
    * @Date: 2021/11/27
    */
    UserDto loadByName(String username);

    /**
    * @Description: Eliminar usuario
    * @Param: [id]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void delUser(Long id);

    /**
    * Comentario en espanol
    * @Param: [sysUser]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void enabledUser(SysUser sysUser);

    /**
    * @Description: Cambiar contrasena del usuario
    * @Param: [jsonObject]
    * @return: void
    * @Author: starao
    * @Date: 2022/10/6
    */
    void updatePassword(JSONObject jsonObject);
}
