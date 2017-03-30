package com.yibo.aop.test.ioc;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yibo.aop.service.IHelloWorldService;

//classpath*的使用：当项目中有多个classpath路径，并同时加载多个classpath路径下（此种情况多数不会遇到）的文件，*就发挥了作用，如果不加*，则表示仅仅加载第一个classpath路径，代码片段： 
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:aop/ioc/applicationContext.xml"})  
public class TestAopIoc {

	@Resource
	IHelloWorldService helloworldService;
	
	@Test
	public void testHelloworld() {
		helloworldService.sayHello();
	}

	//前置通知
	@Test
	public void testSchemaBeforeAdvice() {
		System.out.println("======================================");
//		helloworldService.sayBefore("before");
		helloworldService.sayBefore("before", 1000);
		System.out.println("======================================");
	}

	//后置返回通知
	@Test
	public void testSchemaAfterReturningAdvice() {
		System.out.println("======================================");
//		helloworldService.sayAfterReturning();
		helloworldService.sayAfterReturningSecond();
		System.out.println("======================================");
	}

	//环绕通知
	@Test
	public void testSchemaAroundAdvice() {
		System.out.println("======================================");
//		helloworldService.sayAround("haha");
		helloworldService.sayAround2("haha1", "haha2");
		System.out.println("======================================");
	}

	
	//Advisor
	@Test
	public void testSchemaAdvisor() {
		System.out.println("======================================");
		helloworldService.sayAdvisorBefore("haha");
		System.out.println("======================================");
	}

}
