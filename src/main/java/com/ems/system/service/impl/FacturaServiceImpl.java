package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.StringUtil;
import com.ems.system.entity.Factura;
import com.ems.system.entity.FacturaDetalle;
import com.ems.system.entity.dto.FacturaDetalleDto;
import com.ems.system.entity.dto.FacturaDto;
import com.ems.system.entity.dto.QueryDto;
import com.ems.system.mapper.FacturaDetalleMapper;
import com.ems.system.mapper.FacturaMapper;
import com.ems.system.service.FacturaDetalleService;
import com.ems.system.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de la logica de negocio de facturas.
 */
@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaMapper facturaMapper;

    private final FacturaDetalleMapper facturaDetalleMapper;

    private final FacturaDetalleService facturaDetalleService;

    @Override
    public IPage<Factura> queryFacturaTable(QueryDto queryDto) {
        Page<Factura> page = new Page<>();
        page.setCurrent(queryDto.getCurrentPage());
        page.setSize(queryDto.getSize());
        return facturaMapper.queryFacturaTable(page, queryDto.getBlurry());
    }

    @Override
    public FacturaDto getFactura(Long id) {
        Factura factura = facturaMapper.selectById(id);
        if (factura == null) {
            throw new BadRequestException("La factura no existe");
        }

        FacturaDto facturaDto = new FacturaDto();
        facturaDto.setId(factura.getId());
        facturaDto.setNombre(factura.getNombre());
        facturaDto.setNit(factura.getNit());
        facturaDto.setTotal(factura.getTotal());

        List<FacturaDetalle> detalles = facturaDetalleService.findByFacturaId(id);
        List<FacturaDetalleDto> detalleDtos = new ArrayList<>();
        for (FacturaDetalle detalle : detalles) {
            FacturaDetalleDto detalleDto = new FacturaDetalleDto();
            detalleDto.setId(detalle.getId());
            detalleDto.setFacturaId(detalle.getFacturaId());
            detalleDto.setProducto(detalle.getProducto());
            detalleDto.setPrecio(detalle.getPrecio());
            detalleDto.setCantidad(detalle.getCantidad());
            detalleDto.setSubtotal(detalle.getSubtotal());
            detalleDtos.add(detalleDto);
        }
        facturaDto.setDetalles(detalleDtos);
        return facturaDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editFactura(FacturaDto facturaDto) {
        validarFactura(facturaDto);

        Factura factura = new Factura();
        factura.setId(facturaDto.getId());
        factura.setNombre(facturaDto.getNombre());
        factura.setNit(facturaDto.getNit());
        factura.setTotal(calcularTotal(facturaDto.getDetalles()));

        if (factura.getId() != null) {
            facturaMapper.updateById(factura);
            facturaDetalleService.deleteByFacturaId(factura.getId());
        } else {
            facturaMapper.insert(factura);
        }

        for (FacturaDetalleDto detalleDto : facturaDto.getDetalles()) {
            FacturaDetalle detalle = new FacturaDetalle();
            detalle.setFacturaId(factura.getId());
            detalle.setProducto(detalleDto.getProducto());
            detalle.setPrecio(obtenerPrecio(detalleDto));
            detalle.setCantidad(obtenerCantidad(detalleDto));
            detalle.setSubtotal(calcularSubtotal(detalle));
            facturaDetalleMapper.insert(detalle);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delFactura(Long id) {
        facturaDetalleService.deleteByFacturaId(id);
        facturaMapper.deleteById(id);
    }

    private void validarFactura(FacturaDto facturaDto) {
        if (facturaDto == null) {
            throw new BadRequestException("Los datos de la factura son obligatorios");
        }
        if (StringUtil.isBlank(facturaDto.getNombre())) {
            throw new BadRequestException("El nombre es obligatorio");
        }
        if (StringUtil.isBlank(facturaDto.getNit())) {
            throw new BadRequestException("El NIT es obligatorio");
        }
        if (CollectionUtils.isEmpty(facturaDto.getDetalles())) {
            throw new BadRequestException("La factura debe tener al menos un detalle");
        }
        for (FacturaDetalleDto detalle : facturaDto.getDetalles()) {
            if (StringUtil.isBlank(detalle.getProducto())) {
                throw new BadRequestException("El nombre del producto es obligatorio");
            }
            if (obtenerCantidad(detalle) <= 0) {
                throw new BadRequestException("La cantidad debe ser mayor a cero");
            }
            if (obtenerPrecio(detalle).compareTo(BigDecimal.ZERO) <= 0) {
                throw new BadRequestException("El precio debe ser mayor a cero");
            }
        }
    }

    private BigDecimal calcularTotal(List<FacturaDetalleDto> detalles) {
        BigDecimal total = BigDecimal.ZERO;
        for (FacturaDetalleDto detalleDto : detalles) {
            BigDecimal precio = obtenerPrecio(detalleDto);
            BigDecimal cantidad = BigDecimal.valueOf(obtenerCantidad(detalleDto));
            total = total.add(precio.multiply(cantidad));
        }
        return total;
    }

    private BigDecimal calcularSubtotal(FacturaDetalle detalle) {
        return detalle.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad()));
    }

    private BigDecimal obtenerPrecio(FacturaDetalleDto detalleDto) {
        return detalleDto.getPrecio() == null ? BigDecimal.ZERO : detalleDto.getPrecio();
    }

    private Integer obtenerCantidad(FacturaDetalleDto detalleDto) {
        return detalleDto.getCantidad() == null ? 0 : detalleDto.getCantidad();
    }
}
