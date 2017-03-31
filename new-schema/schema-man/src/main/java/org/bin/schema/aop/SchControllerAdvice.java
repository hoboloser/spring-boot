package org.bin.schema.aop;

import org.bin.schema.exception.BussinessException;
import org.bin.schema.util.sms.SmsValException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <p>Title:SchControllerAdvice</p>
 * <p>Description:统一异常处理</p>
 * @author binH
 * @date 2017年3月23日 下午1:50:13
 */
@ControllerAdvice
public class SchControllerAdvice extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger("EXCEPTION_LOGGER");
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String handle(Exception e) {
//		if (e instanceof BussinessException) {
//			BussinessException bussinessException = (BussinessException) e;
//		}else {
		logger.info("【系统异常】{}", e.getMessage());
//		}
		return null;
	}

}
