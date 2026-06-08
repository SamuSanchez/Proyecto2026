package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ems.common.constant.CommonConstants;
import com.ems.common.exception.BadRequestException;
import com.ems.system.entity.SysRole;
import com.ems.system.entity.SysRoleMenu;
import com.ems.system.entity.dto.RoleMenuDto;
import com.ems.system.mapper.SysRoleMapper;
import com.ems.system.mapper.SysRoleMenuMapper;
import com.ems.system.service.SysRoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 14:37
 **/
@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    private final SysRoleMenuMapper roleMenuMapper;

    private final SysRoleMapper roleMapper;

    /**
     * @param roleId
     * Comentario en espanol
     * @Param: [roleId]
     * @return: java.util.List<com.ems.system.entity.SysRoleMenu>
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public List<SysRoleMenu> getMenuByRoleId(Long roleId) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, roleId);
        return roleMenuMapper.selectList(wrapper);
    }

    /**
     * @param roleMenuDto
     * @Description: Autorizar menu del rol
     * @Param: [roleMenuDto]
     * @return: void
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    @Transactional
    public void editMenuRoleByRoleId(RoleMenuDto roleMenuDto) {
        Long roleId = roleMenuDto.getRoleId();
        // Comentario en espanol
        SysRole role = roleMapper.selectById(roleId);
        if (role == null){
            throw new BadRequestException("El rol actual no existe");
        }
        if (CommonConstants.ROLE_ADMIN.equals(role.getRoleCode())){
            throw new BadRequestException("El superadministrador tiene todos los permisos, no requiere autorizacion");
        }
        // Comentario en espanol
        deleteByRoleId(roleId);
        // Comentario en espanol
        if (!CollectionUtils.isEmpty(roleMenuDto.getMenuIds())){
            roleMenuDto.getMenuIds().forEach(menuId -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleId);
                roleMenuMapper.insert(roleMenu);
            });
        }
    }

    /**
     * @param roleId
     * Comentario en espanol
     * @Param: [roleId]
     * @return: void
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public void deleteByRoleId(Long roleId) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, roleId);
        roleMenuMapper.delete(wrapper);
    }
}
