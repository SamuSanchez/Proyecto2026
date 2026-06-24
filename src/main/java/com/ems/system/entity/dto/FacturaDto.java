package com.ems.system.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO usado para transportar la factura completa: cabecera y detalle.
 */
@Setter
@Getter
public class FacturaDto {

    private Long id;

    private String nombre;

    private String nit;

    private BigDecimal total;

    private List<FacturaDetalleDto> detalles;
}
