package com.ems.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ems.common.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Entidad que representa los productos comprados en una factura.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "app_factura_detalle")
public class FacturaDetalle extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long facturaId;

    private String producto;

    private BigDecimal precio;

    private Integer cantidad;

    private BigDecimal subtotal;
}
