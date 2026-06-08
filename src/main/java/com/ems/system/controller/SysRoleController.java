package com.ems.system.controller;

import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.ResultUtil;
import com.ems.common.utils.StringUtil;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.SysRole;
import com.ems.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 17:03
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys")
public class SysRoleController extends ResultUtil {

    private final SysRoleService roleService;

    /**
    * @Description: Obtener lista de roles
    * @Param: [blurry]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Obtener lista de roles")
    @GetMapping("/role/table")
    public ResponseEntity<Object> getRoleList(String blurry){
        try {
            return success(roleService.getRoleList(blurry));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Editar rol
    * @Param: [role]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Editar rol")
    @PostMapping("/role/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> editRole(@RequestBody SysRole role){
        try {
            String tag = StringUtil.getEditType(role.getId());
            roleService.editRole(role);
            return success(tag);
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Eliminar rol
    * @Param: [id]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Eliminar rol")
    @DeleteMapping("/role/del")
    public ResponseEntity<Object> delRole(Long id){
        try {
            roleService.delRole(id);
            return success("Eliminacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * Comentario en espanol
    * @Param: [userId]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Obtener lista de seleccion de roles")
    @GetMapping("/role/select")
    public ResponseEntity<Object> getAllRoleForXm(Long userId){
        try {
            return success(roleService.getAllRoleForXm(userId));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }
}
