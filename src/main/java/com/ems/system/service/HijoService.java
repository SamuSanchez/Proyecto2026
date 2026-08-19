package com.ems.system.service;

import com.ems.system.entity.Hijo;

import java.util.List;

/**
 * Servicio para gestionar hijos.
 */
public interface HijoService {

    List<Hijo> listByMadre(String cim);

    void editHijo(Hijo hijo);

    void delHijo(String cih);

    void deleteByMadre(String cim);
}
