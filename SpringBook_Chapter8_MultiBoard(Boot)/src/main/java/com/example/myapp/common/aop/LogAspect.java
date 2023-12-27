package com.example.myapp.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect {

	@AfterReturning(pointcut="execution(* com.example.myapp..BoardService.*(..))", returning="result")
	public void afterLog(JoinPoint joinPoint, Object result) {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String resultString = "None";
		if(result!=null) {
			resultString = result.toString();
		}
		log.info("AOP After: method-{}, result-{}", methodName, resultString);
	}
}
