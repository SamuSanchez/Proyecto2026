package com.ems.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ems.system.entity.Factura;
import com.ems.system.entity.dto.FacturaDto;
import com.ems.system.entity.dto.QueryDto;

/**
 * Servicio para gestionar facturas.
 */
public interface FacturaService {

    IPage<Factura> queryFacturaTable(QueryDto queryDto);

    FacturaDto getFactura(Long id);

    void editFactura(FacturaDto facturaDto);

    void delFactura(Long id);
}
