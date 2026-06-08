package com.ems.system.service;

import com.ems.system.entity.SysRoleMenu;
import com.ems.system.entity.dto.RoleMenuDto;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a interface
 * @author: starao
 * @create: 2021-11-27 14:26
 **/
public interface SysRoleMenuService {

    /**
    * Comentario en espanol
    * @Param: [roleId]
    * @return: java.util.List<com.ems.system.entity.SysRoleMenu>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<SysRoleMenu> getMenuByRoleId(Long roleId);

    /**
    * @Description: Autorizar menu del rol
    * @Param: [roleMenuDto]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void editMenuRoleByRoleId(RoleMenuDto roleMenuDto);

    /**
    * Comentario en espanol
    * @Param: [roleId]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void deleteByRoleId(Long roleId);
}
