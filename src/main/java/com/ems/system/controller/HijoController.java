package com.ems.system.controller;

import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.ResultUtil;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.Hijo;
import com.ems.system.service.HijoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar hijos.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys")
public class HijoController extends ResultUtil {

    private final HijoService hijoService;

    @Log("Consultar hijos por madre")
    @GetMapping("/hijo/list")
    public ResponseEntity<Object> listByMadre(String cim) {
        try {
            return success(hijoService.listByMadre(cim));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Editar hijo")
    @PostMapping("/hijo/edit")
    public ResponseEntity<Object> editHijo(@RequestBody Hijo hijo) {
        try {
            hijoService.editHijo(hijo);
            return success("Operacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Eliminar hijo")
    @DeleteMapping("/hijo/del")
    public ResponseEntity<Object> delHijo(String cih) {
        try {
            hijoService.delHijo(cih);
            return success("Eliminacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }
}
