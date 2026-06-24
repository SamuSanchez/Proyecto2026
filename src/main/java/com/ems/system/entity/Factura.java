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
 * Entidad que representa la cabecera de la factura.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "app_factura")
public class Factura extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String nombre;

    private String nit;

    private BigDecimal total;
}
