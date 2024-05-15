package com.demo.redis.jedis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @Author: huangzh
 * @Date: 2024/5/13 13:52
 **/
@Slf4j
@RestController
@RequestMapping("jedis")
public class JedisController {

    @Value("${redis-ip}")
    private String redisIp;

    @Value("${redis-port}")
    private Integer redisPort;

    @Value("${redis-pwd}")
    private String redisPwd;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        Jedis jedisClient = null;
        String value = null;
        try {
            // 初始jedis客户端
            jedisClient = new Jedis(redisIp, redisPort);
            // 设置密码
            jedisClient.auth(redisPwd);
            log.info("jedis客户端登陆成功");
            String key = "current";
            long currentTimeMillis = System.currentTimeMillis();
            jedisClient.set(key, Long.toString(currentTimeMillis));
            value = jedisClient.get(key);
            log.info("jedis查询所得{}：{}", key, value);
        } catch (Exception e) {
            log.error("exception: ", e);
        } finally {
            // 关闭客户端
            jedisClient.close();
            log.info("jedis客户端关闭成功");
        }
        return value;
    }

}
