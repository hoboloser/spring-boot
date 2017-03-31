package org.bin.schema;

public class Desc {

	
	/**
	 * RestFul
	 * SpringBoot + Mybatis
	 * Jedis
	 * Jedis Lock
	 * Protostuff
	 * logback
	 * AOP
	 * 
	 * 
	 * aop request
	 * advice exception
	 * 
	 * 
	 * 压测 ：jmeter
	 */
	
	/**
	 * 

CREATE PROCEDURE seckill.execute_seckill
(in v_stock_id bigint,in v_user_id bigint,
	in v_phone bigint,
	in v_kill_time timestamp,out r_result int)
	BEGIN
		DECLARE insert_count int DEFAULT 0;
		START TRANSACTION;
		insert ignore into seckill_order
			(`stock_id`,`use_id`,`use_phone`,`seckill_time`)
			values (v_stock_id,v_user_id,v_phone,v_kill_time);
		select row_count() into insert_count;
		IF (insert_count = 0) THEN
			ROLLBACK;
			set r_result = -1;
		ELSEIF (insert_count < 0) THEN
			ROLLBACK;
			set r_result = -2;
		ELSE
			update seckill_stock
				set number = number -1
			where stock_id = v_stock_id
				and end_time > v_kill_time
				and start_time < v_kill_time
				and number > 0;
			select row_count() into insert_count;
			IF (insert_count = 0) THEN
				ROLLBACK;
				set r_result = -3;
			ELSEIF (insert_count < 0) THEN	
				ROLLBACK;
				set r_result = -4;
			ELSE
				COMMIT;
				set r_result = 1;
			END IF;
		END IF;
	END;
	


set @r_result = -10;

CALL schema_man.execute_seckill(4,4,18267128903,now(),@r_result);

select @r_result;
	 */
}
