create table if not exists user_system
(
    id                                 bigint unsigned not null comment '主键',
    gmt_create                         datetime(3) not null default current_timestamp(3) comment '创建时间',
    gmt_modified                       datetime(3) not null default current_timestamp(3) on update current_timestamp(3) comment '修改时间',
    deleted                            tinyint(1) not null default 0 comment '是否删除，1-是，0-否',
    code                               varchar(32) not null comment '用户系统编码',
    name                               varchar(32) not null comment '用户系统名称',
    description                        varchar(128) comment '用户系统描述',
    primary key (id),
    index idx_gmtCreate(gmt_create),
    index idx_gmtModified(gmt_modified),
    unique uk_code(code),
    index idx_name(name)
) engine=InnoDB default charset=utf8mb4 comment='用户系统表';