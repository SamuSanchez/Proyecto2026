package com.ems.system.controller;

import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.ResultUtil;
import com.ems.common.utils.StringUtil;
import com.ems.logs.annotation.Log;
import com.ems.system.entity.dto.FacturaDto;
import com.ems.system.entity.dto.QueryDto;
import com.ems.system.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar facturas.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys")
public class FacturaController extends ResultUtil {

    private final FacturaService facturaService;

    @Log("Consultar lista de facturas")
    @GetMapping("/factura/table")
    public ResponseEntity<Object> queryFacturaTable(QueryDto queryDto) {
        try {
            return success(facturaService.queryFacturaTable(queryDto));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Consultar factura por id")
    @GetMapping("/factura/info")
    public ResponseEntity<Object> getFactura(Long id) {
        try {
            return success(facturaService.getFactura(id));
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Editar factura")
    @PostMapping("/factura/edit")
    public ResponseEntity<Object> editFactura(@RequestBody FacturaDto facturaDto) {
        try {
            String str = StringUtil.getEditType(facturaDto.getId());
            facturaService.editFactura(facturaDto);
            return success(str);
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }

    @Log("Eliminar factura")
    @DeleteMapping("/factura/del")
    public ResponseEntity<Object> delFactura(Long id) {
        try {
            facturaService.delFactura(id);
            return success("Eliminacion exitosa");
        } catch (BadRequestException e) {
            return fail(e.getMsg());
        }
    }
}
