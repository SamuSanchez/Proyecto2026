package com.ems.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ems.common.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad que representa la tabla app_hijo.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("app_hijo")
public class Hijo extends BaseEntity {

    /**
     * Carnet de identidad del hijo.
     */
    @TableId
    private String cih;

    /**
     * Carnet de identidad de la madre.
     */
    private String cim;

    /**
     * Nombre del hijo.
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
