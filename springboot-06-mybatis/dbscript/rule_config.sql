use mybatis;
CREATE TABLE `rule_config`(
    `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `manager` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '项目负责人',
    `alarmUser` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '告警人',
    `alarmPhone` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '告警人电话',
    `is_enable` INT(10) NOT NULL DEFAULT 1 COMMENT '是否有效 1：有效 0：无效',
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `modify_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    `deleted` TINYINT(4) NOT NULL DEFAULT 0 comment '0:启用 1：不启用',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COMMENT = '规则配置表';