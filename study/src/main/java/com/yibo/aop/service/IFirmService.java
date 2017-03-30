package com.yibo.aop.service;

/**
 * 用户业务接口
 * @author Yibo Liu
 * @version 2017年3月24日
 */
public interface IFirmService {
    /**
     * 在需要权限的目标方法上，使用PrivilegeInfo注解，配置权限为save
     */
    public void save();
    /**
     * 在需要权限的目标方法上，使用PrivilegeInfo注解，配置权限为update
     */
    public void update();
    /**
     * 不需要权限的目标方法上，则不添加PrivilegeInfo注解
     * 在切面中，默认用户拥有权限
     */
    public void get();
}