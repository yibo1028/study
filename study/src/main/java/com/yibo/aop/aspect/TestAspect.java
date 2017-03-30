package com.yibo.aop.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yibo.aop.annotation.BindWeiXinUserAction;
import com.yibo.aop.annotation.TestAnnotation;
import com.yibo.aop.annotation.TestAnnotationParamter;
import com.yibo.aop.entity.TestBean;

/**
 * 切面类
 * 
 * @author Yibo Liu
 * @version 2017年3月24日
 */
@Aspect
@Component
public class TestAspect {

	public static final int NOT_FOUND_INDEX = -1;
	
	  //声明AOP切入点，凡是使用了XXXOperateLog的方法均被拦截
    @Pointcut("execution(* com.yibo.aop.web..*.*(..))")
    public void testPointcut() {
        System.out.println("我是一个切入点");
    }

    /**
     * 在所有标注@Log的地方切入
     * @param joinPoint
     */
    @Before("testPointcut()")
    public void beforeExec(JoinPoint joinPoint) {
    	System.out.println("前置通知");
    }
    

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@Around(value = "@annotation(com.yibo.aop.annotation.TestAnnotation)"
			+ " && @annotation(org.springframework.web.bind.annotation.RequestMapping) "
			+ " && execution(* com.yibo.aop.web..*.*(..)) ")
	public void handle(ProceedingJoinPoint joinPoint) throws Throwable {
//		http://www.cnblogs.com/peida/archive/2013/04/26/3038503.html
//		String toString();         //连接点所在位置的相关信息
//	    String toShortString();     //连接点所在位置的简短相关信息
//	    String toLongString();     //连接点所在位置的全部相关信息
//	    Object getThis();         //返回AOP代理对象
//	    Object getTarget();       //返回目标对象
//	    Object[] getArgs();       //返回被通知方法参数列表
//	    Signature getSignature();  //返回当前连接点签名
//	    SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
//	    String getKind();        //连接点类型
//	    StaticPart getStaticPart(); //返回连接点静态部分

		//返回被织入增强处理的目标对象
		Object target = joinPoint.getTarget();
		//Object getThis：返回AOP框架为目标对象生成的代理对象
		Object proxyObj = joinPoint.getThis();
		//获取目标对象对应的类名
		Class targetClass = joinPoint.getTarget().getClass();
		//获取实现类继承的接口名
		Class[] c = joinPoint.getTarget().getClass().getInterfaces();
	    
	    
//		//返回当前连接点签名
		Signature signature = joinPoint.getSignature();
		//返回当前连接点签名
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		 //获取目标对象上正在执行的方法名
		String methodName = joinPoint.getSignature().getName();
		// 方法的参数
		Object[] args = joinPoint.getArgs(); 
		//获取到这个类上面的方法全名
	    //Method[] methods = joinPoint.getSignature().getDeclaringType().getMethods();
	    //获取方法名为methodName，并且参数为methodSignature.getParameterTypes()的方法
		Method method = targetClass.getMethod(methodName, methodSignature.getParameterTypes());
		
		//返回该程序元素上存在的所有注解。
		Annotation[] annotations = method.getAnnotations();
		//返回方法上参数的所有注解
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		
		
		//返回直接存在于此元素上的所有注释。与此接口中的其他方法不同，该方法将忽略继承的注释。（如果没有注释直接存在于此元素上，则返回长度为零的一个数组。）该方法的调用者可以随意修改返回的数组；这不会对其他调用者返回的数组产生任何影响。
		Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
		//获取方法的参数
		Class<?>[] parameterTypes = method.getParameterTypes();

		
		
		//示例：判断这个类上面的注释是否是TestAnnotation这个自定义的注解，如果是返回这个注解，如果不是返回null
		if (joinPoint.getTarget().getClass().getAnnotation(TestAnnotation.class) != null) {
			// 获取到这个类上的注解
			TestAnnotation testAnnotation = joinPoint.getTarget().getClass().getAnnotation(TestAnnotation.class);
			// 输出这个类上的注解的值
			System.out.println("注释在实现类上的annotation：" + testAnnotation.value());

		}
		// 示例：判断这个接口上是否存在此注解
		if (c.length > 0 &&  c[0].getAnnotation(TestAnnotation.class) != null) {
			TestAnnotation an = (TestAnnotation) c[0].getAnnotation(TestAnnotation.class);
			System.out.println("注解对象所实现接口上的注解值：" + an.value());
		}
			       
				
		//示例：返回改程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null。
		//<T extends Annotation> T getAnnotation(Class<T> annotationClass): 
		Annotation  annotation = method.getAnnotation(TestAnnotation.class);				
		Annotation  annotation1 = this.getAnnotation(signature, TestAnnotation.class);
		Annotation  annotation3 = this.getAnnotation(signature, TestAnnotationParamter.class);
		
		//示例：获取方法的注解并进行逻辑处理(TestAnnotation)
		TestAnnotation  testAnnotation = this.getAnnotation(signature, TestAnnotation.class);
		if(testAnnotation != null) {
			String name = testAnnotation.name();
			String value = testAnnotation.value();
			int num = testAnnotation.num();
			//逻辑处理
		}
		
		//示例：获取参数的注解并进行逻辑处理(testAnnotationParamter)
		for (int i = 0; i < parameterTypes.length; i++) {
			Object parameter = parameterTypes[i];
			for (Annotation annotationParam : parameterAnnotations[i]) {
				if (annotationParam instanceof TestAnnotationParamter) {
					TestAnnotationParamter testAnnotationParamter = (TestAnnotationParamter) annotationParam;
					String paramName = testAnnotationParamter.paramName();
					String value = testAnnotationParamter.defaultValue();
					boolean requiredUserNo = testAnnotationParamter.requiredUserNo();
					//逻辑处理
					TestBean testBean = this.getObjectFromArgs(joinPoint, TestBean.class);	
//				if(parameter instanceof TestBean) {
//					TestBean testBean = (TestBean) parameter;
					String userNo = testBean.getUserNo();
					if(requiredUserNo && (userNo == null || "".equals(userNo))) {
						
						throw new RuntimeException("用户编号不能为空");
					}


					BindWeiXinUserAction[] actions = testAnnotationParamter.actions();
					for (BindWeiXinUserAction action : actions) {
						switch (action) {
						case NOT_EXIST_USER:
							//逻辑处理
							break;
						case EXIST_USER:
							//逻辑处理
							break;
						default:
							throw new RuntimeException("没找到该bindAction");
						}
					}
				}
			}

		}
		
		//示例：获取参数类型为TestBean的参数
		TestBean testBean = this.getObjectFromArgs(joinPoint, TestBean.class);	
		
		
		HttpServletRequest request = getRequestByJoinPoint(joinPoint);
		HttpServletResponse response = getResponseByJoinPoint(joinPoint);
		String requestUri = request.getRequestURI();
		Cookie[] cookies = request.getCookies();

		joinPoint.proceed();
	}

	/**
	 * 根据signature获取方法的参数：类型为cls类型的参数的索引,然后从参数列表中根据索引获取cls参数
	 * @param joinPoint
	 * @param cls
	 * @return
	 */
	protected <T> T getObjectFromArgs(JoinPoint joinPoint, Class<T> cls) {
		int index = findClsIndex(joinPoint.getSignature(), cls);
		if (index == NOT_FOUND_INDEX) {
			return null;
		}
		return (T) joinPoint.getArgs()[index];
	}

	/**
	 * 根据signature获取方法：是否有cl类型的注解，有则返回该注解，没有返回null
	 * @param signature
	 * @param cls
	 * @return
	 */
	protected <T extends Annotation> T getAnnotation(Signature signature, Class<T> cls) {
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		return method.getAnnotation(cls);
	}

	/**
	 * 获取参数注解类型为TestAnnotationParamter的注解cls
	 * @param cls
	 * @param annotationGroup
	 * @return
	 */
	private <T extends Annotation> T getAnnotationByGroup(Annotation[] annotationGroup) {
		for (Annotation annotation : annotationGroup) {
			if (annotation instanceof TestAnnotationParamter) {
				return (T) annotation;
			}
		}
		return null;
	}
	
	
	/**
	 * 根据signature获取方法的参数：类型为cls类型的参数的索引
	 * @param signature
	 * @param cls
	 * @return
	 */
	protected int findClsIndex(Signature signature, Class<?> cls) {
		int index = NOT_FOUND_INDEX;
		MethodSignature methodSignature = (MethodSignature) signature;
		Class<?>[] paramTypes = methodSignature.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			if (cls == paramTypes[i]) {
				index = i;
			}
		}
		return index;
	}

	protected HttpServletRequest getRequestByJoinPoint(JoinPoint joinPoint) {
		HttpServletRequest request = getObjectFromArgs(joinPoint, HttpServletRequest.class);
		if (request == null) {
			request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		}
		return request;
	}

	protected HttpServletResponse getResponseByJoinPoint(JoinPoint joinPoint) {
		HttpServletResponse response = getObjectFromArgs(joinPoint, HttpServletResponse.class);
		return response;
	}
}