package org.bin.schema.exception;

import org.bin.schema.enums.ExceptionEnum;

public class SystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474515010638496410L;

	private ExceptionEnum eenum;
	
	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}
	
	public SystemException(ExceptionEnum eenum) {
		super(eenum.getName());
		this.eenum = eenum;
	}

	public ExceptionEnum getEenum() {
		return eenum;
	}

	public void setEenum(ExceptionEnum eenum) {
		this.eenum = eenum;
	}
}
