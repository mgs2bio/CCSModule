package com.in28minutes.springboot.microservice.example.currencyconversion.aop;

import java.math.BigDecimal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;

import com.in28minutes.springboot.microservice.example.currencyconversion.CurrencyConversionBean;

@Aspect  //Aspect is a collection of when you want to intercept a method call (PointCut) and what to do (advice)
@Configuration
public class RequestLogger {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*//@Before("execution (* com.in28minutes.springboot.microservice.example.currencyconversion.*.*(..))")  //Pointcut is an expression used to define when the method call should be intercepted 
	@After("@annotation(LogExecutionTime)")
	public void AfterCall(JoinPoint jointPoint) {
		//Advice is a logic you want to invoke when a method call is intercepted
		long start = System.currentTimeMillis();
		 
	    try {
			Object proceed = ((MethodInvocationProceedingJoinPoint) jointPoint).proceed(); //proceed will force to execute the JointPoint method again even after it is already executed
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    logger.warn(jointPoint.getSignature() + " executed in " + executionTime + "ms");
	}
	*/
	
	/*@Before("@annotation(LogExecutionTime)")
	public void beforeCall(JoinPoint jointPoint) {
		//Advice
		long start = System.currentTimeMillis();
		 
	    try {
			Object proceed = ((MethodInvocationProceedingJoinPoint) jointPoint).proceed(); //proceed will force to execute the JointPoint method again even after it is already executed
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    logger.warn(jointPoint.getSignature() + " executed in " + executionTime + "ms");
	}*/
	
	//@Around("@annotation(LogExecutionTime)") //@Around is the most important and powerful advice. This advice surrounds the join point method and we can also choose whether to execute the join point method or not
	//@Around("execution(* com.in28minutes.springboot.microservice.example.currencyconversion.CurrencyConversionController.convertCurrencyFeign(String, String, BigDecimal))")
	@Around("execution(* com.in28minutes.springboot.microservice.example.currencyconversion.CurrencyConversionController.convertCurrencyFeign(..))")
	public Object aroundCall(JoinPoint jointPoint) {
		//Advice
		long start = System.currentTimeMillis();
		Object proceed = null;
		 
	    try {
	    	proceed = ((MethodInvocationProceedingJoinPoint) jointPoint).proceed(); //proceed will force to execute the JointPoint method again even after it is already executed
		   
	    } catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    logger.warn(jointPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}

}
