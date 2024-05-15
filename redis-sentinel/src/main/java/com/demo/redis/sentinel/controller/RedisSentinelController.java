package com.demo.redis.sentinel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: huangzh
 * @Date: 2024/5/13 16:20
 **/
@Slf4j
@RestController
@RequestMapping("/redisSentinel")
public class RedisSentinelController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        String key = "current";
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        // 在主机7101宕机的情况下，向redis发起写/读请求，将会不断的尝试重连6382（Reconnecting, last destination was 10.15.18.88:7101）
        // 直至哨兵检测到7102宕机，并选举出新的主机6381，将会把请求重连至6381，完成读/写请求（Reconnected to 10.15.18.88:7102）
        stringRedisTemplate.opsForValue().set(key, format.format(new Date()));
        String value = stringRedisTemplate.opsForValue().get(key);
        log.info("stringRedisTemplate查询所得{}：{}", key, value);
        return key + ":" + value;
    }
}
