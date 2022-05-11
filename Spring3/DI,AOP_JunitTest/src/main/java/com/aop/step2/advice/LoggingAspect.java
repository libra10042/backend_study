package com.aop.step2.advice;

import com.aop.step2.util.UtilLib;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

	public void displayLine() {
		System.out.println(UtilLib.lpad("*", 50, "*"));
	}


	@Before("execution(public * com.aop.step2.entity.*.*(..))")
	public void before(JoinPoint joinPoint) {

		displayLine();

		String signatureStr = joinPoint.getSignature().getName();
		System.out.println("### STEP1 : @Before [" + signatureStr + "] 메서드 실행 전처리 수행");
		for (Object arg : joinPoint.getArgs()) {
			System.out.println("### STEP1 : @Before [" + signatureStr + "] 아규먼트 : " + arg);
		}
		System.out.println(" ===> A");

		displayLine();
	}

	@Around("execution(public * com.aop.step2.entity.*.*(..))")
	public Object afterReturning(ProceedingJoinPoint joinPoint) throws Throwable {

		displayLine();
		String signatureStr = joinPoint.getSignature().getName();
		System.out.println("### STEP2 : "+ "@Around [" + signatureStr + "] 메서드 Around 실행 시작");

		try {
			// 핵심기능
			Object result = joinPoint.proceed();
			System.out.println("=> Around 실행 결과 : "+result);
			return result;
		} finally {
			System.out.println("### STEP2 : "+ "@Around [" + signatureStr + "] 메서드 Around 실행 종료");
			displayLine();
		}

	}

//	@AfterReturning(pointcut = "execution(public * com.aop.step2.entity.*.*(..))", returning = "ret")
	public void afterReturning(JoinPoint joinPoint, Object ret) {

		displayLine();
		String signatureStr = joinPoint.getSignature().getName();
		System.out.println("### STEP3 : @AfterReturning [" + signatureStr + "] 메서드 실행 후처리 수행");
		System.out.println("### STEP3 : ### @AfterReturning [" + signatureStr + "] 리턴값 = " + ret);
		displayLine();
	}


//	@AfterThrowing(pointcut = "execution(public * com.aop.step2.entity.*.*(..))", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		displayLine();
		String signatureStr = joinPoint.getSignature().getName();
		System.out.println("### STEP4 :  @AfterThrowing [" + signatureStr + "] 메서드 실행 중 예외 발생");
		System.out.println("### STEP4 : @AfterThrowing [" + signatureStr + "] 예외 = " + ex);
		displayLine();
	}


	@After("execution(public * com.aop.step2.entity.*.*(..))")
	public void afterFinally(JoinPoint joinPoint) {
		displayLine();
		String signatureStr = joinPoint.getSignature().getName();
		System.out.println("### STEP5 :  @After [" + signatureStr + "] 메서드 실행 완료");
		displayLine();
	}
}
