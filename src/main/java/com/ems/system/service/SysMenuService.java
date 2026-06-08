package com.ems.system.service;


import com.alibaba.fastjson2.JSONArray;
import com.ems.system.entity.SysMenu;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a interface
 * @author: starao
 * @create: 2021-11-27 14:24
 **/
public interface SysMenuService {

    /**
    * @Description: Obtener arbol de menus
    * @Param: [roles]
    * @return: com.alibaba.fastjson.JSONArray
    * @Author: starao
    * @Date: 2021/11/27
    */
    JSONArray getMenuTree(List<String> roles);

    /**
    * @Description: Editar menu
    * @Param: [sysMenu]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void editMenu(SysMenu sysMenu);

    /**
    * @Description: Eliminar menu
    * @Param: [id]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    void delMenu(Long id);

    /**
    * Comentario en espanol
    * @Param: [roles]
    * @return: java.util.List<com.ems.system.entity.SysMenu>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<SysMenu> queryAllMenus(List<String> roles);

    /**
    * @Description: Obtener lista de menus
    * @Param: [blurry]
    * @return: com.alibaba.fastjson.JSONArray
    * @Author: starao
    * @Date: 2021/12/11
    */
    JSONArray getMenuTable(String blurry);

    /**
    * Comentario en espanol
    * @Param: [currentRoles]
    * @return: java.util.List<java.lang.String>
    * @Author: starao
    * @Date: 2022/1/19
    */
    List<String> getUrlsByRoles(List<String> currentRoles);

    /**
    * @Description: Obtener lista de permisos
    * @Param: []
    * @return: java.util.List<java.lang.String>
    * @Author: starao
    * @Date: 2022/10/6
    */
    List<String> getPermission();

    /**
    * @Description: Obtener arbol desplegable de menus
    * @Param: []
    * @return: com.alibaba.fastjson.JSONArray
    * @Author: starao
    * @Date: 2022/11/9
    */
    JSONArray getMenuTreeSelect();
}
