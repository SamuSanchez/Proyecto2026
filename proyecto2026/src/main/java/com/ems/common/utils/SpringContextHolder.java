package com.ems.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ems-admin-boot
 * @description: this is a class
 * @author: starao
 * @create: 2021-11-27 12:05
 **/
@Slf4j
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static final List<CallBack> CALL_BACKS = new ArrayList<>();

    private static boolean addCallback = true;

    /**
    * Comentario en espanol
    * @Param: [requiredType]
    * @return: T
    * @Author: starao
    * @Date: 2021/11/27
    */
    public static <T> T getBean(Class<T> requiredType){
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
    * Comentario en espanol
    * @Param: []
    * @return: void
    * @Author: starao
    * @Date: 2021/11/27
    */
    private static void assertContextInjected(){
        if (applicationContext == null){
            throw new IllegalStateException("applicationContext no fue inyectado, " +
                    "registra SpringContextHolder en la clase de arranque de Spring Boot.");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextHolder.applicationContext != null){
            log.info("ApplicationContext en SpringContextHolder fue sobrescrito, " +
                    "ApplicationContext original: " + SpringContextHolder.applicationContext);
        }
        SpringContextHolder.applicationContext = applicationContext;
        if (addCallback){
            for (CallBack callBack : SpringContextHolder.CALL_BACKS) {
                callBack.executor();
            }
        }
        SpringContextHolder.addCallback = false;
    }

    @Override
    public void destroy() {
        log.info("Limpiar ApplicationContext en SpringContextHolder: " + applicationContext);
        applicationContext = null;
    }
}
