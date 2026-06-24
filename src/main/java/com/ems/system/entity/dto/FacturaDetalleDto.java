package com.ems.system.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO usado para recibir y devolver el detalle de una factura.
 */
@Setter
@Getter
public class FacturaDetalleDto {

    private Long id;

    private Long facturaId;

    private String producto;

    private BigDecimal precio;

    private Integer cantidad;

    private BigDecimal subtotal;
}
