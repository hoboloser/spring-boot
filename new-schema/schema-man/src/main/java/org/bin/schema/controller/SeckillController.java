package org.bin.schema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bin.schema.exception.BussinessException;
import org.bin.schema.service.SeckillService;
import org.bin.schema.util.sms.SmsCodeVal;
import org.bin.schema.util.sms.SmsValException;
import org.bin.schema.util.sms.TelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/seckill")
public class SeckillController extends BaseController{

	@Autowired
	private SeckillService seckillService;
	
	@PostMapping(value="/{id}/excution")
	public void excute(@PathVariable Long id,@RequestParam("userId") Long userId,@RequestParam("userPhone") Long userPhone,HttpServletResponse response){
		try{
			String md5 = seckillService.printOutUrlWithMd5(id);
			seckillService.excuteSeckill(id, userId, userPhone, md5);
		}catch(BussinessException e){
			writeAjaxJSONResponse(e, response);
		}
		
		writeAjaxJSONResponse("success", response);
	}
	
	@PostMapping(value="/{id}/pro/excution")
	public void excutePro(@PathVariable Long id,@RequestParam("userId") Long userId,@RequestParam("userPhone") Long userPhone,HttpServletResponse response){
		try{
			String md5 = seckillService.printOutUrlWithMd5(id);
			seckillService.excuteSeckillProduce(id, userId, userPhone, md5);
		}catch(BussinessException e){
			writeAjaxJSONResponse(e, response);
		}
		
		writeAjaxJSONResponse("success", response);
	}
	
	@GetMapping(value="/get")
	public void excute(HttpServletResponse response){
		
		writeAjaxJSONResponse("success", response);
	}
	
	
	@GetMapping(value="/send")
	public void send(HttpServletResponse response,HttpServletRequest request){
		String mobile = TelUtil.getTel();
//		try{
			SmsCodeVal.getInstance().canSendCode(mobile, request);
//			writeAjaxJSONResponse("success:"+mobile, response);
//		}catch(SmsValException e){
//			writeAjaxJSONResponse("error:"+mobile+" ; errormsg : "+ e.getEnums().getCode(), response);
//		}
	}
	
}
