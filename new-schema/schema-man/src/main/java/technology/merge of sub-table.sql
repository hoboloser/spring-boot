-- MERGE分表法

insert into schtest(`name`,sex) values ('mack','0');

insert into schtest(`name`,sex) select `name`,sex from schtest;

-- 分表设计
DROP table IF EXISTS tb_schtest1;
create table tb_schtest1(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest2;
create table tb_schtest2(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest3;
create table tb_schtest3(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest4;
create table tb_schtest4(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest5;
create table tb_schtest5(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest10;
create table tb_schtest10(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest6;
create table tb_schtest6(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest7;
create table tb_schtest7(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest8;
create table tb_schtest8(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

DROP table IF EXISTS tb_schtest9;
create table tb_schtest9(
    id bigint primary key auto_increment ,
    `name` varchar(20),
    sex tinyint not null default '0'
)ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

-- merge 总表
DROP table IF EXISTS tb_schtest;
create table tb_schtest(
id bigint primary key auto_increment ,
`name` varchar(20),
sex tinyint not null default '0'
)ENGINE=MERGE UNION=(tb_schtest1,tb_schtest2,tb_schtest3,tb_schtest4,tb_schtest5,tb_schtest6,tb_schtest7,tb_schtest8,tb_schtest9,tb_schtest10) INSERT_METHOD=LAST CHARSET=utf8 AUTO_INCREMENT=1 ;


-- 数据处理
insert into tb_schtest1(id,`name`,sex) select id,`name`,sex from schtest where id%10=0;
insert into tb_schtest2(id,`name`,sex) select id,`name`,sex from schtest where id%10=1;
insert into tb_schtest3(id,`name`,sex) select id,`name`,sex from schtest where id%10=2;
insert into tb_schtest4(id,`name`,sex) select id,`name`,sex from schtest where id%10=3;
insert into tb_schtest5(id,`name`,sex) select id,`name`,sex from schtest where id%10=4;
insert into tb_schtest6(id,`name`,sex) select id,`name`,sex from schtest where id%10=5;
insert into tb_schtest7(id,`name`,sex) select id,`name`,sex from schtest where id%10=6;
insert into tb_schtest8(id,`name`,sex) select id,`name`,sex from schtest where id%10=7;
insert into tb_schtest9(id,`name`,sex) select id,`name`,sex from schtest where id%10=8;
insert into tb_schtest10(id,`name`,sex) select id,`name`,sex from schtest where id%10=9;









desc tb_schtest;

select count(*) from schtest;




select * from tb_schtest;

select count(*) from tb_schtest;


select * from tb_schtest where `name` = 'mack';

select * from schtest where `name` = 'mack';