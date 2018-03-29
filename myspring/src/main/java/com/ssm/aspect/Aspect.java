package com.ssm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

	//这样是为了,实现切入点的重用
	@Pointcut("execution(public * *.save(..))")
	public void pointcut(){

	}

	@Before("pointcut()")
	public void before(JoinPoint jp){
		System.out.println("前置通知");
	}

	@After("pointcut()")
	public void afterMethod(JoinPoint jp){
		System.out.println("后置通知");
	}

	@AfterThrowing("pointcut()")
	public void afterThrow(JoinPoint jp){
		System.out.println("异常通知");
	}
}
