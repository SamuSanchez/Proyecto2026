package com.ems.system.controller;

import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.ResultUtil;
import com.ems.common.utils.SecurityUtil;
import com.ems.common.utils.StringUtil;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.SysMenu;
import com.ems.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 17:01
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys")
public class SysMenuController extends ResultUtil {

    private final SysMenuService menuService;

    /**
    * @Description: Obtener arbol de menus
    * @Param: []
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Obtener arbol de menus")
    @GetMapping("/menu/tree")
    public ResponseEntity<Object> getMenuTree(){
        try {
            List<String> roles = SecurityUtil.getCurrentRoles();
            return success(menuService.getMenuTree(roles));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Obtener lista de permisos
    * @Param: []
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2022/10/6
    */
    @Log(value = "Obtener lista de permisos")
    @GetMapping("/menu/permission")
    public ResponseEntity<Object> getPermission(){
        try {
            return success(menuService.getPermission());
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * Comentario en espanol
    * @Param: []
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @GetMapping("/menu/all")
    public ResponseEntity<Object> queryAllMenus(){
        try {
            List<String> roles = SecurityUtil.getCurrentRoles();
            return success(menuService.queryAllMenus(roles));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Obtener lista de menus
    * @Param: [blurry]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/12/11
    */
    @Log("Obtener lista de menus")
    @GetMapping("/menu/table")
    public ResponseEntity<Object> getMenuTable(String blurry){
        try {
            return success(menuService.getMenuTable(blurry));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Editar menu
    * @Param: [sysMenu]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Editar menu")
    @PostMapping("/menu/edit")
    public ResponseEntity<Object> editMenu(@RequestBody SysMenu sysMenu){
        try {
            String str = StringUtil.getEditType(sysMenu.getId());
            menuService.editMenu(sysMenu);
            return success(str);
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Eliminar menu
    * @Param: [id]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Eliminar menu")
    @DeleteMapping("/menu/del")
    public ResponseEntity<Object> delMenu(Long id){
        try {
            menuService.delMenu(id);
            return success("Eliminacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log(value = "Obtener arbol desplegable de menus")
    @GetMapping("/menu/select")
    public ResponseEntity<Object> getMenuTreeSelect(){
        try {
            return success(menuService.getMenuTreeSelect());
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }
}
