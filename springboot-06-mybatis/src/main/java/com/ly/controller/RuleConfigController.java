package com.ly.controller;

import com.ly.mapper.RuleConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleConfigController {

    @Autowired
    RuleConfigMapper ruleConfigMapper;


}
