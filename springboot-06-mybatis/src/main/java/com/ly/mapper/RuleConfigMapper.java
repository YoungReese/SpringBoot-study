package com.ly.mapper;

import com.ly.pojo.RuleConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 这个注解表示这是一个mybatis的mapper类，可以在程序主入口使用@MapperScan("com.ly.mapper")替代
@Repository
public interface RuleConfigMapper {

    /**
     * 查询所有的
     * @return
     */
    @Select("select * from rule_config")
    List<RuleConfig> queryRuleConfigs();

    /**
     * 利用 alarmUser 进行模糊查询
     * @param alarmUser
     * @return
     * CONCAT('%',#{name},'%')
     */
    @Select("select * from rule_config where is_enable > -1 and alarm_user like CONCAT('%',#{alarmUser},'%')")
    List<RuleConfig> fuzzyQueryByAlarmUser(@Param("alarmUser") String alarmUser);

    /**
     * 利用 alarmPhone 进行模糊查询
     * @param alarmPhone
     * @return
     */
    @Select("select * from rule_config where is_enable > -1 and alarm_phone like CONCAT('%',#{alarmPhone},'%')")
    List<RuleConfig> fuzzyQueryByAlarmPhone(@Param("alarmPhone") String alarmPhone);



}
