package com.yibo.aop.test.aspectj;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yibo.aop.aspect.PrivilegeAspectJ;
import com.yibo.aop.entity.FirmPrivilege;
import com.yibo.aop.service.IFirmService;

/**
* aop+注解权限控制测试类
 * @author Yibo Liu
 * @version 2017年3月24日
 */
//classpath*的使用：当项目中有多个classpath路径，并同时加载多个classpath路径下（此种情况多数不会遇到）的文件，*就发挥了作用，如果不加*，则表示仅仅加载第一个classpath路径，代码片段： 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:aop/aspectj/applicationContext-privilege.xml" })
public class TestPrivilegeAspectJ {
    /**
     * 客户端直接调用这个Service的方法，而不需要关心权限问题
     */
	@Resource
    private IFirmService firmService;
	
	@Resource
    private PrivilegeAspectJ privilegeAspect2;

    /**
     * 在初始化方法中，初始化firmService
     * 同时为用户赋上原始权限，这个在项目中，会使用别的方式实现，这里只是模拟，就不搞那么复杂了
     */
    @Before
    public void init() {

        List<FirmPrivilege> privileges = new ArrayList<FirmPrivilege>();
        //privileges.add(new FirmPrivilege("save"));
        privileges.add(new FirmPrivilege("update"));
        privilegeAspect2.setPrivileges(privileges);
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
