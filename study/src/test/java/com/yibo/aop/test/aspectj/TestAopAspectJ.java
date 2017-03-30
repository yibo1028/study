package com.yibo.aop.test.aspectj;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yibo.aop.service.IHelloWorldService;

//classpath*的使用：当项目中有多个classpath路径，并同时加载多个classpath路径下（此种情况多数不会遇到）的文件，*就发挥了作用，如果不加*，则表示仅仅加载第一个classpath路径，代码片段： 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:aop/aspectj/applicationContext.xml"})
public class TestAopAspectJ {

	@Resource
	IHelloWorldService helloworldService;

	//前置通知
	@Test
	public void testAnnotationBeforeAdvice() {
		System.out.println("======================================");
		helloworldService.sayBefore("before");
		System.out.println("======================================");
	}
	
	

	//后置返回通知
	@Test
	public void testAfterReturningAdvice() {
		System.out.println("======================================");
		helloworldService.sayAfterReturning();
		helloworldService.sayAfterReturningSecond();
		System.out.println("======================================");
	}

	//环绕通知
	@Test
	public void testAroundAdvice() {
		System.out.println("======================================");
		helloworldService.sayAround("haha");
		helloworldService.sayAround2("haha1", "haha2");
		System.out.println("======================================");
	}

	
	//后置异常通知
	@Test(expected = RuntimeException.class)
	public void testAfterThrowingAdvice() {
		System.out.println("======================================");
		helloworldService.sayAfterThrowing();
		System.out.println("======================================");
	}

}
