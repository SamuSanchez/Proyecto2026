package com.ems.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ems.common.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;

/**
 * Entidad que representa la cabecera de la Casas.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "casas")
public class Casas extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long idCasa;

    private String nombreCasa;

    private String direccion;

    private String ciudad;

    private long cantidadPisos;

    private Date fechaConstruccion;
}
