package com.ems.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ems.system.entity.SysUser;
import com.ems.system.entity.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a interface
 * @author: starao
 * @create: 2021-11-27 14:22
 **/
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
    * Comentario en espanol
    * @Param: [username]
    * @return: com.ems.system.entity.dto.UserDto
    * @Author: starao
    * @Date: 2021/11/27
    */
    UserDto loadByName(@Param("username") String username);

    /**
    * @Description: Consultar lista de usuarios
    * @Param: [blurry]
    * @return: java.util.List<com.ems.system.entity.dto.UserDto>
    * @Author: starao
    * @Date: 2021/12/4
    */
    IPage<UserDto> queryUserTable(Page<?> page, @Param("blurry") String blurry);
}
