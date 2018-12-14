package com.example.springbootaop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：jmyang
 * @ Date       ：Created in 2018/12/14
 * @ Description：
 * @ throws
 */
@RestController
public class AopTestController {


    //前置通知
    @RequestMapping("/testBeforeService")
    public String testBeforeService(String key,String value){

        return "key:"+key+",value:"+value;
    }

    @RequestMapping("/testAroundService")
    public String testAroundService(String key){

        return "环绕通知："+key;
    }
}
