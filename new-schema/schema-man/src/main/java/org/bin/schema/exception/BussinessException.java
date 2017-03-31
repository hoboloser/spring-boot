package org.bin.schema.exception;

import org.bin.schema.enums.ExceptionEnum;

public class BussinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private ExceptionEnum eenum;
	
	public BussinessException(){
		super();
	}
	
	public BussinessException(ExceptionEnum eenum){
		super(eenum.getName());
		this.eenum = eenum;
	}
	
	public BussinessException(String message){
		super(message);
	}

	public ExceptionEnum getEenum() {
		return eenum;
	}

	public void setEenum(ExceptionEnum eenum) {
		this.eenum = eenum;
	}

}
