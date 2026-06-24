package com.ems.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ems.system.entity.Factura;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Mapper de la cabecera de factura.
 */
@Repository
public interface FacturaMapper extends BaseMapper<Factura> {

    IPage<Factura> queryFacturaTable(IPage<?> page, @Param("blurry") String blurry);
}
