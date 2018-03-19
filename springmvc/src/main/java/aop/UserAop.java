package aop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class UserAop {
	private Logger logger=LogManager.getLogger(UserAop.class);
	
	@Pointcut("execution (* service.UserService.getUser(..))")
	public void getUser() {
	}
	@Around("getUser()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("around:befroe get user");
		Object o=joinPoint.proceed();
		logger.info("around:after get user");
		return o;
	}
	
//	@Before("getUser()")
//	public void beforeGetUser() {
//		logger.info("befroe get user");
//	}
//	
//	@After("getUser()")
//	public void afterGetUser() {
//		logger.info("after get user");
//	}
}
