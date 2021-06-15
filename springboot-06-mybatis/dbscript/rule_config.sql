use mybatis;
CREATE TABLE `rule_config`(
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `manager` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '项目负责人',
    `alarm_user` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '告警人',
    `alarm_phone` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '告警人电话',
    `is_enable` INT(10) NOT NULL DEFAULT 0 COMMENT '是否有效 1：有效 0：无效',
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `modify_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT 0 comment '0:启用 1：不启用',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COMMENT = '规则配置表';

insert into `rule_config` (manager, alarm_user, alarm_phone)
values
('liyang', 'liyang','18711112222'),
('zhangsan', 'liyang,zhangsan','18711112222,18822223333'),
('lisi', 'liyang,lisi','18711112222,18822224444'),
('wangwu', 'liyang,wangwu','18711112222,18822225555');