package com.ems.system.service;


import com.alibaba.fastjson2.JSONArray;
import com.ems.system.entity.SysRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a interface
 * @author: starao
 * @create: 2021-11-27 14:26
 **/
public interface SysRoleService {

    /**
    * @Description: Obtener lista de roles
    * @Param: [blurry]
    * @return: java.util.List<com.ems.system.entity.SysRole>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<SysRole> getRoleList(String blurry);

    /**
    * @Description: Editar rol
    * @Param: [role]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void editRole(SysRole role);

    /**
    * @Description: Eliminar rol
    * @Param: [id]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void delRole(Long id);

    /**
    * Comentario en espanol
    * @Param: [userId]
    * @return: com.alibaba.fastjson.JSONArray
    * @Author: starao
    * @Date: 2021/11/27
    */
    JSONArray getAllRoleForXm(Long userId);

    /**
    * Comentario en espanol
    * @Param: [userId]
    * @return: java.util.List<com.ems.system.entity.SysRole>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<SysRole> getRoleByUserId(Long userId);

    /**
    * Comentario en espanol
    * @Param: [id]
    * @return: java.util.List<org.springframework.security.core.GrantedAuthority>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<GrantedAuthority> getRolesByUser(Long id);
}
