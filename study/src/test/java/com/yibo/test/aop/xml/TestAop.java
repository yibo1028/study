package com.yibo.test.aop.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yibo.aop.service.IHelloWorldService;

public class TestAop {

	ApplicationContext ctx = new ClassPathXmlApplicationContext("aop/xml/applicationContext.xml");
	IHelloWorldService helloworldService = ctx.getBean("helloWorldService", IHelloWorldService.class);
	
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
