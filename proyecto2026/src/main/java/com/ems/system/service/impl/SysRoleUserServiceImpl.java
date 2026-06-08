package com.ems.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ems.common.exception.BadRequestException;
import com.ems.system.entity.SysRoleUser;
import com.ems.system.mapper.SysRoleUserMapper;
import com.ems.system.service.SysRoleUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 14:43
 **/
@Service
@RequiredArgsConstructor
public class SysRoleUserServiceImpl implements SysRoleUserService {

    private final SysRoleUserMapper roleUserMapper;

    /**
     * @param roleId
     * Comentario en espanol
     * @Param: [roleId]
     * @return: java.util.List<com.ems.system.entity.SysRoleUser>
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public List<SysRoleUser> getRoleUserByRoleId(Long roleId) {
        LambdaQueryWrapper<SysRoleUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleUser::getRoleId, roleId);
        return roleUserMapper.selectList(wrapper);
    }

    /**
     * @param userId
     * @param roleIds
     * Comentario en espanol
     * @Param: [userId, roles]
     * @return: void
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    @Transactional
    public void edit(Long userId, List<String> roleIds) {
        // Comentario en espanol
        LambdaQueryWrapper<SysRoleUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleUser::getUserId, userId);
        roleUserMapper.delete(wrapper);
        // Comentario en espanol
        roleIds.forEach(role -> {
            SysRoleUser roleUser = new SysRoleUser();
            roleUser.setUserId(userId);
            roleUser.setRoleId(Long.parseLong(role));
            roleUserMapper.insert(roleUser);
        });
    }

    /**
     * @param userId
     * Comentario en espanol
     * @Param: [userId]
     * @return: java.util.List<com.ems.system.entity.SysRoleUser>
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public List<SysRoleUser> getRoleUserByUserId(Long userId) {
        LambdaQueryWrapper<SysRoleUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleUser::getUserId, userId);
        return roleUserMapper.selectList(wrapper);
    }

    /**
     * @param userId
     * Comentario en espanol
     * @Param: [id]
     * @return: void
     * @Author: starao
     * @Date: 2021/11/27
     */
    @Override
    public void deleteByUserId(Long userId) {
        LambdaQueryWrapper<SysRoleUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleUser::getUserId, userId);
        roleUserMapper.delete(wrapper);
    }
}
