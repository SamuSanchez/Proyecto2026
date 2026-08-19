package com.ems.system.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para transportar datos de hijo entre frontend y backend.
 */
@Setter
@Getter
public class HijoDto {

    private String cih;

    private String cim;

    private String nombre;

    private String ap;

    private String am;
}
