package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ems.system.entity.FacturaDetalle;
import com.ems.system.mapper.FacturaDetalleMapper;
import com.ems.system.service.FacturaDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacion del servicio de detalle de factura.
 */
@Service
@RequiredArgsConstructor
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    private final FacturaDetalleMapper facturaDetalleMapper;

    @Override
    public List<FacturaDetalle> findByFacturaId(Long facturaId) {
        LambdaQueryWrapper<FacturaDetalle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FacturaDetalle::getFacturaId, facturaId)
                .orderByAsc(FacturaDetalle::getId);
        return facturaDetalleMapper.selectList(wrapper);
    }

    @Override
    public void deleteByFacturaId(Long facturaId) {
        LambdaQueryWrapper<FacturaDetalle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FacturaDetalle::getFacturaId, facturaId);
        facturaDetalleMapper.delete(wrapper);
    }
}
