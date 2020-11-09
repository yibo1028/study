package com.yibo.test.aop.xml;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yibo.aop.aspect.PrivilegeAspect;
import com.yibo.aop.entity.FirmPrivilege;
import com.yibo.aop.service.IFirmService;

/**
* aop+注解权限控制测试类
 * @author Yibo Liu
 * @version 2017年3月24日
 */
public class TestPrivilege {
    /**
     * 客户端直接调用这个Service的方法，而不需要关心权限问题
     */
    private IFirmService firmService;

    /**
     * 在初始化方法中，初始化firmService
     * 同时为用户赋上原始权限，这个在项目中，会使用别的方式实现，这里只是模拟，就不搞那么复杂了
     */
    @Before
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/xml/applicationContext-privilege.xml");
        firmService = (IFirmService) context.getBean("firmService");

        /*
         * 给用户添加默认权限
         */
        PrivilegeAspect privilegeAspect = (PrivilegeAspect) context.getBean("privilegeAspect");
        List<FirmPrivilege> privileges = new ArrayList<FirmPrivilege>();
        //privileges.add(new FirmPrivilege("save"));
        privileges.add(new FirmPrivilege("update"));
        privilegeAspect.setPrivileges(privileges);
    }

    /**
     * 客户端直接调用Service中的方法，而不需要关心权限问题，会有切面去做
     */
    @Test
    public void test() {
        firmService.save();
        firmService.update();
        firmService.get();
    }
}
