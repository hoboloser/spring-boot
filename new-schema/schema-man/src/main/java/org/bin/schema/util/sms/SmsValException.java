package org.bin.schema.util.sms;

public class SmsValException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SmsValEnums enums ;
	
	public SmsValException(){}

	public SmsValException(SmsValEnums enums) {
		this.enums = enums;
	}

	public SmsValEnums getEnums() {
		return enums;
	}

	public void setEnums(SmsValEnums enums) {
		this.enums = enums;
	}
	
	@Override
	public String getMessage() {
		return enums.getErrorMsg();
	}
}
