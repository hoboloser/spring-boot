CREATE DATABASE schema_man;

use schema_man;

CREATE table seckill_stock(
	stock_id bigint(11) NOT NULL AUTO_INCREMENT comment '库存主键',
	`name` varchar(120) NOT NULL comment '库存内容',
	`count` bigint(11) NOT NULL default 0 comment '库存容量',
	create_time timestamp NOT NULL default now() comment '秒杀库存创建时间',
	start_time timestamp NOT NULL comment '秒杀开启时间',
	end_time timestamp NOT NULL comment '秒杀结束时间',
	primary key(stock_id),
	index v_start_time(start_time),
	index v_ent_time(end_time),
	index v_create_time(create_time)
) engine = innoDB default charset=UTF8 auto_increment = 1;


CREATE table user_account(
	user_id bigint(11) NOT NULL AUTO_INCREMENT comment '用户主键',
	`name` varchar(64) NOT NULL comment '姓名',
	`account` varchar(32) NOT NULL comment '登录用户名',
	password varchar(120) NOT NULL comment '登录密码',
	mobile varchar(64) NOT NULL comment '用户电话',
	address varchar(64) comment '用户住址',
	zip_code varchar(16)  comment '用户邮编',
	create_time timestamp default now() comment '创建时间',
	update_time timestamp comment '更新时间',
	last_login timestamp comment '最后登录时间',
	primary key(user_id)
)ENGINE = innoDB default charset=UTF8 auto_increment = 100;


CREATE table seckill_order(
	stock_id bigint(11) NOT NULL comment '秒杀库存的主键',
	use_id bigint(11) NOT NULL comment '用户ID',
	use_phone varchar(16) NOT NULL comment '用户手机号',
	create_time timestamp default now() comment '创建时间',
	seckill_time timestamp comment '秒杀时间',
	primary key(stock_id,use_id)
) ENGINE = innoDB default charset UTF8;


insert into user_account(`name`,`account`,password,mobile)
values
('张三','zhangsan','123456','13838380438'),
('李四','lisi','123456','15015238937'),
('王五','wangwu','123456','13304331343');

insert into seckill_stock(`name`,`count`,start_time,end_time)
values
('1元抢iphone',100,'2017-03-18 00:00:00','2017-03-18 00:30:00'),
('1000元抢游轮',200,'2017-03-18 07:00:00','2017-03-18 07:30:00'),
('99元抢iphone18',800,'2017-03-18 12:00:00','2017-03-18 12:30:00'),
('999元抢越南媳妇',10,'2017-03-19 00:00:00','2017-03-19 00:30:00'),
('2元抢小米',300,'2017-03-19 12:00:00','2017-03-18 12:30:00');


