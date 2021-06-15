package com.ly.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleConfig {
    /**
     * 自增主键
     */
    private int id;

    /**
     * 项目负责人
     */
    private String manager;

    /**
     * 告警人（工号）
     */
    private String alarmUser;

    /**
     * 告警电话
     */
    private String alarmPhone;

    /**
     * 是否有效
     * 默认1：有效   0：无效
     */
    private String isEnable;

    /**
     * 记录创建时间
     */
    private Timestamp createTime;

    /**
     * 记录更新时间
     */
    private Timestamp modifyTime;

    /**
     * 是否启用该表
     * 默认0：启用   1：不启用
     */
    private int deleted;
}
