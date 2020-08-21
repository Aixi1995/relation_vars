-- 产品：
create table prod_define
(
    id             bigint(20) unsigned primary key NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    prod_name      varchar(10)                     not null comment '产品名称',
    prod_num       varchar(10)                     not null comment '产品编号',
    create_time    datetime                        not null default CURRENT_TIMESTAMP comment '创建时间',
    create_user_id varchar(50)                     not null comment '创建者id',
    update_time    datetime                        not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '创建时间',
    update_user_id varchar(50)                     not null comment '创建者id'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 comment '产品定义表';

-- 字段
create table field_define
(
    id             bigint(20) unsigned primary key NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    prod_num       varchar(10)                     not null comment '字段所属的产品编号',
    field_name     varchar(10)                     not null comment '字段名称',
    pattern        varchar(255)                    not null comment '字段校验正则',
    block_list     varchar(50) comment '字段的过滤黑名单',
    create_time    datetime                        not null default CURRENT_TIMESTAMP comment '创建时间',
    create_user_id varchar(50)                     not null comment '创建者id',
    update_time    datetime                                 default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '创建时间',
    update_user_id varchar(50) comment '创建者id'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 comment '字段定义表';
-- 关系

