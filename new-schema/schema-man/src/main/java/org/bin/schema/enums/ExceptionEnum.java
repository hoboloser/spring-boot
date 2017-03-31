package org.bin.schema.enums;

public enum ExceptionEnum {

	SUCCESS(0,"秒杀成功！"),
	BECHANGE(3330,"秒杀接口被篡改！"),
	NULL(3331,"秒杀内容不存在！"),
	NOTSTART(3332,"秒杀尚未开启！"),
	ISEND(3333,"秒杀已结束！"),
	FAILED(3334,"秒杀失败！"),
	FAILEDEXC(3335,"秒杀异常！"),
	ORDEREXC(3336,"订单异常！"),
	REPEAT(3337,"重复秒杀！"),
	SYSTEMEXCEPTION(3338,"系统异常！"),
	;
	
	private Integer code;
	
	private String name;
	
	private ExceptionEnum(Integer code,String name){
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
