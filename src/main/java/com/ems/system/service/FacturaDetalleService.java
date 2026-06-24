package com.ems.system.service;

import com.ems.system.entity.FacturaDetalle;

import java.util.List;

/**
 * Servicio para gestionar el detalle de una factura.
 */
public interface FacturaDetalleService {

    List<FacturaDetalle> findByFacturaId(Long facturaId);

    void deleteByFacturaId(Long facturaId);
}
