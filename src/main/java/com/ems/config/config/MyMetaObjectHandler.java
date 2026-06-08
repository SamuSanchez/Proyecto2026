package com.ems.config.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 13:11
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    /**
    * Comentario en espanol
    * @Param: [metaObject]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
    }

    /**
    * Comentario en espanol
    * @Param: [metaObject]
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
    }
}
