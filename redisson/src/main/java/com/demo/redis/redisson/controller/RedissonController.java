package com.demo.redis.redisson.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: huangzh
 * @Date: 2024/5/14 13:56
 **/
@Slf4j
@RestController
@RequestMapping("/redisson")
public class RedissonController {

    @Resource
    private RedissonClient redissonClient;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        String key = "current";
        long currentTimeMillis = System.currentTimeMillis();
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(Long.toString(currentTimeMillis));
        String value = bucket.get().toString();
        log.info("redissonClient查询所得{}：{}", key, value);
        return key + ":" + value;
    }
}
