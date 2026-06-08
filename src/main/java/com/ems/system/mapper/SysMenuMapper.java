package com.ems.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ems.system.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a interface
 * @author: starao
 * @create: 2021-11-27 14:18
 **/
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
    * @Description: Obtener arbol de menus
    * @Param: [roles]
    * @return: java.util.List<com.ems.system.entity.SysMenu>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<SysMenu> getMenuTree(@Param("roles") List<String> roles);

    /**
    * Comentario en espanol
    * @Param: [roles]
    * @return: java.util.List<java.lang.String>
    * @Author: starao
    * @Date: 2021/11/27
    */
    List<String> getMenuUrlByRole(@Param("roles") List<String> roles);

    /**
    * @Description: Obtener lista de permisos
    * @Param: [roles]
    * @return: java.util.List<java.lang.String>
    * @Author: starao
    * @Date: 2022/10/6
    */
    List<String> getPermission(@Param("roles") List<String> roles);
}
