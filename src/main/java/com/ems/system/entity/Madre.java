package com.ems.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ems.common.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa la tabla app_madre.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("app_madre")
public class Madre extends BaseEntity {

    /**
     * Carnet de identidad de la madre.
     */
    @TableId
    private String cim;

    /**
     * Nombre de la madre.
     */
    private String nombre;

    /**
     * Apellido paterno.
     */
    private String ap;

    /**
     * Apellido materno.
     */
    private String am;
}
