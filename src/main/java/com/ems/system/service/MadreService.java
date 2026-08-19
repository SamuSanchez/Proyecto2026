package com.ems.system.service;

import com.ems.system.entity.Madre;
import com.ems.system.entity.dto.MadreDto;

import java.util.List;

/**
 * Servicio para gestionar madres.
 */
public interface MadreService {

    List<Madre> listMadres();

    MadreDto getMadre(String cim);

    void editMadre(Madre madre);

    void delMadre(String cim);
}
