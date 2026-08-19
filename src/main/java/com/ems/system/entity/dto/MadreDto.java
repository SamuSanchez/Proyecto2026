package com.ems.system.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO para transportar una madre con la lista de sus hijos.
 */
@Setter
@Getter
public class MadreDto {

    private String cim;

    private String nombre;

    private String ap;

    private String am;

    private List<HijoDto> hijos;
}
