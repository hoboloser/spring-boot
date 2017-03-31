package org.bin.schema.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.bin.schema.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestAspect {

	private Logger logger = LoggerFactory.getLogger("REQUEST_LOGGER");

	@Pointcut(value = "execution(public * org.bin.schema.controller..*.*(..))")
	public void requestLog() {
	}

	@Before(value = "requestLog()")
	public void before(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		String log = StringUtil.strAdd("HTTP.URL : ", request.getRequestURL().toString(),
				System.getProperty("line.separator"),
				"HTTP.METHOD : ", request.getMethod(),
				System.getProperty("line.separator"),
//				"HTTP.IP : ", request.get(),
//				System.getProperty("line.separator"),
				"HTTP.METHOD : ",request.getMethod(),
				System.getProperty("line.separator"),
				"HTTP.CLASS.METHOD : ",joinPoint.getSignature().getDeclaringTypeName(),
				".", joinPoint.getSignature().getName(),
				System.getProperty("line.separator"),
				"HTTP.PARAMS : ",Arrays.toString(joinPoint.getArgs())
				);
		
		logger.info(log);
	}

	@After(value = "requestLog()")
	public void after(JoinPoint joinPoint) {
		joinPoint.getSignature().getDeclaringTypeName();
	}

}
