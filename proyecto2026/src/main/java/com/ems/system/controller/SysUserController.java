package com.ems.system.controller;


import com.alibaba.fastjson2.JSONObject;
import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.ResultUtil;
import com.ems.common.utils.StringUtil;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.SysUser;
import com.ems.system.entity.dto.QueryDto;
import com.ems.system.entity.dto.UserDto;
import com.ems.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 17:06
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys")
public class SysUserController extends ResultUtil {

    private final SysUserService userService;

    /**
    * @Description: Consultar lista de usuarios
    * @Param: [blurry]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Consultar lista de usuarios")
    @GetMapping("/user/table")
    public ResponseEntity<Object> queryUserTable(QueryDto queryDto){
        try {
            return success(userService.queryUserTable(queryDto));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Editar usuario
    * @Param: [userDto]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Editar usuario")
    @PostMapping("/user/edit")
    public ResponseEntity<Object> editUser(@RequestBody UserDto userDto){
        try {
            String str = StringUtil.getEditType(userDto.getId());
            userService.editUser(userDto);
            return success(str);
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Eliminar usuario
    * @Param: [id]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Eliminar usuario")
    @DeleteMapping("/user/del")
    public ResponseEntity<Object> delUser(Long id){
        try {
            userService.delUser(id);
            return success("Eliminacion exitosa");
        } catch (BadRequestException e) {
            return fail("Error al eliminar");
        }
    }

    /**
    * Comentario en espanol
    * @Param: [sysUser]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Log("Cambiar estado del usuario")
    @PutMapping("/user/enabled")
    public ResponseEntity<Object> enabledUser(@RequestBody SysUser sysUser){
        String str = sysUser.isEnabled() ? "Activar" : "Desactivar";
        try {
            userService.enabledUser(sysUser);
            return success(str + " exitoso");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    /**
    * @Description: Cambiar contrasena del usuario
    * @Param: [jsonObject]
    * @return: org.springframework.http.ResponseEntity<java.lang.Object>
    * @Author: starao
    * @Date: 2022/10/6
    */
    @Log(value = "Cambiar contrasena del usuario")
    @PutMapping("/user/password")
    public ResponseEntity<Object> updatePassword(@RequestBody JSONObject jsonObject){
        try {
            userService.updatePassword(jsonObject);
            return success("Actualizacion exitosa");
        } catch (BadRequestException e) {
            return fail("Error al actualizar");
        }
    }
}
