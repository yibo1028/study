package com.yibo.aop.service;

public interface IHelloWorldService {
	public void sayHello();

	public void sayHello(String param);

	void sayBefore(String param);

	void sayBefore(String param1, Integer param2);
	
	boolean sayAfterReturning();

	String sayAfterReturningSecond();

	void sayAround(String param);

	void sayAround2(String param1, String param2);

	void sayAdvisorBefore(String param);

	boolean sayAfterFinally();

	void sayAfterThrowing();

}
