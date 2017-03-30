package com.yibo.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloWorldAspectJ {
	@Pointcut(value = "execution(* com.yibo..*.sayBefore(..)) && args(param)", argNames = "param")
	public void beforePointcut(String param) {
	}

	@Before(value = "beforePointcut(param)", argNames = "param")
	public void beforeAdvice(String param) {
		System.out.println("===========before advice param:" + param);
	}

	@AfterReturning(value = "execution(* com.yibo..*.sayBefore(..))", 
			pointcut = "execution(* com.yibo..*.sayAfterReturning(..)) or execution(* com.yibo..*.sayAfterReturningSecond(..))", 
			argNames = "retVal", returning = "retVal")
	public void afterReturningAdvice(Object retVal) {
		System.out.println("===========after returning advice retVal:" + retVal);
	}

	
	@AfterThrowing(value = "execution(* com.yibo..*.sayAfterThrowing(..))", 
			argNames = "exception", throwing = "exception")
	public void afterThrowingAdvice(Exception exception) {
		System.out.println("===========after throwing advice exception:" + exception);
	}

	@Around(value = "execution(* com.yibo..*.sayAround(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace" });
		System.out.println("===========around after advice");
		return retVal;
	}
	
	@Around(value = "execution(* com.yibo..*.sayAround2(..))")
	public Object aroundAdvice2(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace1", "replace2"});
		System.out.println("===========around after advice");
		return retVal;
	}

}
