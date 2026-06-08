package com.ems.system.service;

import com.ems.system.entity.SysRoleUser;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a interface
 * @author: starao
 * @create: 2021-11-27 14:28
 **/
public interface SysRoleUserService {

    /**
    * Comentario en espanol
    * @Param: [roleId]
    * @return: java.util.List<com.ems.system.entity.SysRoleUser>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<SysRoleUser> getRoleUserByRoleId(Long roleId);

    /**
    * Comentario en espanol
    * @Param: [userId, roleIds]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void edit(Long userId, List<String> roleIds);

    /**
    * Comentario en espanol
    * @Param: [userId]
    * @return: java.util.List<com.ems.system.entity.SysRoleUser>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<SysRoleUser> getRoleUserByUserId(Long userId);

    /**
    * Comentario en espanol
    * @Param: [userId]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void deleteByUserId(Long userId);
}
