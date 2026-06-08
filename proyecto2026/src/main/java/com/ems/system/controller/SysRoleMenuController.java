package com.ems.system.controller;

import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.ResultUtil;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.dto.RoleMenuDto;
import com.ems.system.service.SysRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 17:05
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("sys")
public class SysRoleMenuController extends ResultUtil {

    private final SysRoleMenuService roleMenuService;

    /**
    * Comentario en espanol
    * @Param: [roleId]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Obtener menu del rol")
    @GetMapping("/role/menu/list")
    public ResponseEntity<Object> getMenuByRoleId(Long roleId){
        try {
            return success(roleMenuService.getMenuByRoleId(roleId));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Autorizar menu del rol
    * @Param: [roleMenuDto]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Autorizar menu del rol")
    @PostMapping("/role/menu/edit")
    public ResponseEntity<Object> editMenuRoleByRoleId(@RequestBody RoleMenuDto roleMenuDto){
        try {
            roleMenuService.editMenuRoleByRoleId(roleMenuDto);
            return success("Autorizacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }
}
