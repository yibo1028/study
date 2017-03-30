package com.yibo.aop.service.impl;

import org.springframework.stereotype.Service;

import com.yibo.aop.service.IHelloWorldService;

@Service
public class HelloWorldService implements IHelloWorldService {
	@Override
	public void sayHello() {
		System.out.println("============Hello World!");
	}
	@Override
	public void sayHello(String param) {
		System.out.println("============Hello World!,param=" + param);
	}
	


	@Override
	public void sayBefore(String param) {
		System.out.println("============say " + param);
	}
	@Override
	public void sayBefore(String param1, Integer param2) {
		System.out.println("============say param1:" + param1 + ",param2:"+param2);
	}
	
	
	
	@Override
	public boolean sayAfterReturning() {
	    System.out.println("============after returning");
	    return true;
	}
	@Override
	public String sayAfterReturningSecond() {
	    System.out.println("============after returning");
	    return "sayAfterReturningSecond";
	}
	
	
	
	
	@Override
	public void sayAround(String param) {
	   System.out.println("============around param:" + param);
	}
	
	@Override
	public void sayAround2(String param1, String param2) {
	   System.out.println("============around param1:" + param1 + ",param2:"+param2);
	}

	
	
	@Override
	public void sayAdvisorBefore(String param) {
	System.out.println("============say " + param);
	}

	
	@Override
	public void sayAfterThrowing() {
	    System.out.println("============before throwing");
	    throw new RuntimeException();
	}

	@Override
	public boolean sayAfterFinally() {
		System.out.println("============before finally");
		throw new RuntimeException();
	}

}
