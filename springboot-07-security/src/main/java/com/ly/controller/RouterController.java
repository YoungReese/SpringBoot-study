package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "views/login";
    }

    @GetMapping("/level1/{id}")
    public String toLevel1(@PathVariable("id") int id) {
        String location = "views/level1/" + id;
        return location;
    }

    @GetMapping("/level2/{id}")
    public String toLevel2(@PathVariable("id") int id) {
        return ("views/level2/" + id);
    }

    @GetMapping("/level3/{id}")
    public String toLevel3(@PathVariable("id") int id) {
        return ("views/level3/" + id);
    }


}
