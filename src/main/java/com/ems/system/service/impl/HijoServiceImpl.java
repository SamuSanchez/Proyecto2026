package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.StringUtil;
import com.ems.system.entity.Hijo;
import com.ems.system.entity.Madre;
import com.ems.system.mapper.HijoMapper;
import com.ems.system.mapper.MadreMapper;
import com.ems.system.service.HijoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacion de la logica de negocio de hijos.
 */
@Service
@RequiredArgsConstructor
public class HijoServiceImpl implements HijoService {

    private final HijoMapper hijoMapper;

    private final MadreMapper madreMapper;

    @Override
    public List<Hijo> listByMadre(String cim) {
        if (StringUtil.isBlank(cim)) {
            throw new BadRequestException("El CI de la madre es obligatorio");
        }

        LambdaQueryWrapper<Hijo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Hijo::getCim, cim);
        wrapper.orderByAsc(Hijo::getCih);
        return hijoMapper.selectList(wrapper);
    }

    @Override
    public void editHijo(Hijo hijo) {
        validarHijo(hijo);

        Madre madre = madreMapper.selectById(hijo.getCim());
        if (madre == null) {
            throw new BadRequestException("La madre seleccionada no existe");
        }

        Hijo existe = hijoMapper.selectById(hijo.getCih());
        if (existe != null) {
            hijoMapper.updateById(hijo);
        } else {
            hijoMapper.insert(hijo);
        }
    }

    @Override
    public void delHijo(String cih) {
        if (StringUtil.isBlank(cih)) {
            throw new BadRequestException("El CI del hijo es obligatorio");
        }
        hijoMapper.deleteById(cih);
    }

    @Override
    public void deleteByMadre(String cim) {
        LambdaQueryWrapper<Hijo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Hijo::getCim, cim);
        hijoMapper.delete(wrapper);
    }

    private void validarHijo(Hijo hijo) {
        if (hijo == null) {
            throw new BadRequestException("Los datos del hijo son obligatorios");
        }
        if (StringUtil.isBlank(hijo.getCih())) {
            throw new BadRequestException("El CI del hijo es obligatorio");
        }
        if (StringUtil.isBlank(hijo.getCim())) {
            throw new BadRequestException("El CI de la madre es obligatorio");
        }
        if (StringUtil.isBlank(hijo.getNombre())) {
            throw new BadRequestException("El nombre del hijo es obligatorio");
        }
        if (StringUtil.isBlank(hijo.getAp())) {
            throw new BadRequestException("El apellido paterno del hijo es obligatorio");
        }
        if (StringUtil.isBlank(hijo.getAm())) {
            throw new BadRequestException("El apellido materno del hijo es obligatorio");
        }
    }
}
