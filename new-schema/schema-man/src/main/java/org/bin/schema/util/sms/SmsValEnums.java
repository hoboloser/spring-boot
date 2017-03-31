package org.bin.schema.util.sms;

public enum SmsValEnums {

	NULL(3000,"电话号码为空！"),
	IP_ERROR(3001,"IP地址有误，请检查您的IP信息！"),
	PHONE_ERROR(3002,"电话号码有误，请检查您的电话号码信息！"),
	IP_BEYOND(3003,"IP发送超出限制！"),
	SEND_BEYOND(3004,"电话号码短信发送超出限制！"),
	TIME_END(3005,"发送时间过短，请稍后再试！"),
	;
	
	private Integer code;
	
	private String msg;
	
	private SmsValEnums(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getErrorMsg(){
		
		return getMsg();
	}
}
