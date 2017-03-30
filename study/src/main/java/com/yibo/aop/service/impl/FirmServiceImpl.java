package com.yibo.aop.service.impl;

import org.springframework.stereotype.Service;

import com.yibo.aop.annotation.PrivilegeInfo;
import com.yibo.aop.service.IFirmService;

/**
 * 用户业务实现
 * @author Yibo Liu
 * @version 2017年3月24日
 */
@Service
public class FirmServiceImpl implements IFirmService {

	/**
	 * 在需要权限的目标方法上，使用PrivilegeInfo注解，配置权限
	 */
	@Override
	@PrivilegeInfo("save")
	public void save() {
		System.out.println("FirmServiceImpl.save()");

	}

	/**
	 * 在需要权限的目标方法上，使用PrivilegeInfo注解，配置权限
	 */
	@Override
	@PrivilegeInfo("update")
	public void update() {
		System.out.println("FirmServiceImpl.update()");

	}

	/**
	 * 不需要权限的目标方法上，则不添加PrivilegeInfo注解 在切面中，默认用户拥有权限
	 */
	@Override
	public void get() {
		System.out.println("FirmServiceImpl.get()");

	}
}
