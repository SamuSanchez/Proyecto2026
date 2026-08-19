package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ems.common.exception.BadRequestException;
import com.ems.common.utils.StringUtil;
import com.ems.system.entity.Hijo;
import com.ems.system.entity.Madre;
import com.ems.system.entity.dto.HijoDto;
import com.ems.system.entity.dto.MadreDto;
import com.ems.system.mapper.MadreMapper;
import com.ems.system.service.HijoService;
import com.ems.system.service.MadreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de la logica de negocio de madres.
 */
@Service
@RequiredArgsConstructor
public class MadreServiceImpl implements MadreService {

    private final MadreMapper madreMapper;

    private final HijoService hijoService;

    @Override
    public List<Madre> listMadres() {
        LambdaQueryWrapper<Madre> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Madre::getCim);
        return madreMapper.selectList(wrapper);
    }

    @Override
    public MadreDto getMadre(String cim) {
        if (StringUtil.isBlank(cim)) {
            throw new BadRequestException("El CI de la madre es obligatorio");
        }

        Madre madre = madreMapper.selectById(cim);
        if (madre == null) {
            throw new BadRequestException("La madre no existe");
        }

        MadreDto madreDto = new MadreDto();
        madreDto.setCim(madre.getCim());
        madreDto.setNombre(madre.getNombre());
        madreDto.setAp(madre.getAp());
        madreDto.setAm(madre.getAm());

        List<Hijo> hijos = hijoService.listByMadre(cim);
        List<HijoDto> hijoDtos = new ArrayList<>();
        for (Hijo hijo : hijos) {
            HijoDto hijoDto = new HijoDto();
            hijoDto.setCih(hijo.getCih());
            hijoDto.setCim(hijo.getCim());
            hijoDto.setNombre(hijo.getNombre());
            hijoDto.setAp(hijo.getAp());
            hijoDto.setAm(hijo.getAm());
            hijoDtos.add(hijoDto);
        }
        madreDto.setHijos(hijoDtos);
        return madreDto;
    }

    @Override
    public void editMadre(Madre madre) {
        validarMadre(madre);

        Madre existe = madreMapper.selectById(madre.getCim());
        if (existe != null) {
            madreMapper.updateById(madre);
        } else {
            madreMapper.insert(madre);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMadre(String cim) {
        if (StringUtil.isBlank(cim)) {
            throw new BadRequestException("El CI de la madre es obligatorio");
        }
        hijoService.deleteByMadre(cim);
        madreMapper.deleteById(cim);
    }

    private void validarMadre(Madre madre) {
        if (madre == null) {
            throw new BadRequestException("Los datos de la madre son obligatorios");
        }
        if (StringUtil.isBlank(madre.getCim())) {
            throw new BadRequestException("El CI de la madre es obligatorio");
        }
        if (StringUtil.isBlank(madre.getNombre())) {
            throw new BadRequestException("El nombre de la madre es obligatorio");
        }
        if (StringUtil.isBlank(madre.getAp())) {
            throw new BadRequestException("El apellido paterno de la madre es obligatorio");
        }
        if (StringUtil.isBlank(madre.getAm())) {
            throw new BadRequestException("El apellido materno de la madre es obligatorio");
        }
    }
}
