package com.yibo.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class HelloWorldAspect {
	// 前置通知
	public void beforeAdvice() {
		System.out.println("===========before advice");
	}

	
	
	// 后置最终通知
	public void afterFinallyAdvice() {
		System.out.println("===========after finally advice");
	}

	
	
	public void beforeAdvice(String param) {
		System.out.println("===========before advice param:" + param);
	}	
	public void beforeAdvice(String param1, Integer param2) {
		System.out.println("===========before advice param1:" + param1 + ",param2:"+param2);
	}

	
	
	public void afterReturningAdvice(Object retVal) {
	    System.out.println("===========after returning advice retVal:" + retVal);
	}

	
	

	public void afterThrowingAdvice(Exception exception) {
		System.out.println("===========after throwing advice exception:" + exception);
	}
	
	
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace" });
		System.out.println("===========around after advice");
		return retVal;
	}
	public Object aroundAdvice2(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace1", "replace2"});
		System.out.println("===========around after advice");
		return retVal;
	}
}
