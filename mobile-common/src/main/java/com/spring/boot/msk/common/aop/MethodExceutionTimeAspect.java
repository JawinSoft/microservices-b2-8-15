package com.spring.boot.msk.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Slf4j
public class MethodExceutionTimeAspect {

	
	@Pointcut("execution(* org.spring.boot.msk.mobile..*.*(..))")
	public void allMethodsInMobileService() {}
	
	
	@Pointcut("execution(* org.spring.boot.msk.country..*.*(..))")
	public void allMethodsInCountryService() {}
	
	//@Pointcut("execution(* org.spring.boot.msk.mobile.accessory..*.*(..))")
	//public void allMethodsInMobileAccessoryService() {}
	
	
	
	@Around(value = ("allMethodsInMobileService() || allMethodsInCountryService() "))
	public Object logExecutionTimeAdvise(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();
		Object targetObject = null;
		
		try {
			targetObject = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			//
			
		}
		
       log.error("Time take to execute "+pjp.getSignature().toLongString()+" is "+(System.currentTimeMillis()- startTime) +" Milli Seconds" );

		return targetObject;
		
	}
	
}
