//package org.bin.schema.test;
//
//import java.util.List;
//
//import org.bin.schema.dao.SeckillStockDAO;
//import org.bin.schema.dao.util.MyBatisDAO;
//import org.bin.schema.entity.SeckillStock;
//import org.bin.schema.exception.BussinessException;
//import org.bin.schema.start.AppStart;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.alibaba.fastjson.JSON;
//
////@RunWith(SpringRunner.class)
////@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)  
//@SpringApplicationConfiguration(classes = AppStart.class)  
//@WebAppConfiguration  
//public class ServiceTest {
//
//	
//	@Before
//	public void bef(){
//		
//	}
////	@Autowired
////	private SeckillService seckillService;
////	@Autowired
////	private SeckillStockDAO seckillStockDAO;
//	@Autowired
//	private MyBatisDAO myBatisDAO;
//	
//	@Test
//	public void test(){
//		try{
//			List list = myBatisDAO.findForList("getSeckillStock");
//			//String md5 = seckillService.printOutUrlWithMd5(1);
//			System.out.println(JSON.toJSONString(list));
////			SeckillStock stock = seckillStockDAO.getStockById(1);
////			System.out.println(JSON.toJSONString(stock));
//		}catch(BussinessException e){
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
