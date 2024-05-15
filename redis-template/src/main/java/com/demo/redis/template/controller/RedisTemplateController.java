package com.demo.redis.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangzh
 * @Date: 2024/5/13 11:54
 **/
@Slf4j
@RestController
@RequestMapping("/redisTemplate")
public class RedisTemplateController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        String key = "current";
        Long currentTimeMillis = System.currentTimeMillis();
        stringRedisTemplate.opsForValue().set(key, currentTimeMillis.toString());
        String value = stringRedisTemplate.opsForValue().get(key);
        log.info("stringRedisTemplate查询所得{}：{}", key, value);
        return key + ":" + value;
    }

}
