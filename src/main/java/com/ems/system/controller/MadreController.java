package com.ems.system.controller;

import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.ResultUtil;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.Madre;
import com.ems.system.service.MadreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar madres.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys")
public class MadreController extends ResultUtil {

    private final MadreService madreService;

    @Log("Consultar lista de madres")
    @GetMapping("/madre/list")
    public ResponseEntity<Object> listMadres() {
        try {
            return success(madreService.listMadres());
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Consultar madre por CI")
    @GetMapping("/madre/info")
    public ResponseEntity<Object> getMadre(String cim) {
        try {
            return success(madreService.getMadre(cim));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Editar madre")
    @PostMapping("/madre/edit")
    public ResponseEntity<Object> editMadre(@RequestBody Madre madre) {
        try {
            madreService.editMadre(madre);
            return success("Operacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Eliminar madre")
    @DeleteMapping("/madre/del")
    public ResponseEntity<Object> delMadre(String cim) {
        try {
            madreService.delMadre(cim);
            return success("Eliminacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }
}
