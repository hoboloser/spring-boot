package org.bin.schema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Title:AppStart</p>
 * <p>Description:@SpringBootApplication  //与@Configuration @EnableAutoConfiguration相同@ComponentScan</p>
 * @author binH
 * @date 2017年3月23日 上午9:40:52
 */
@SpringBootApplication
public class AppStart {

	public static void main(String[] args) {
		SpringApplication.run(AppStart.class,args);
	}
}
