package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig{

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis);
        return new JedisPool(jedisPoolConfig,host,port,timeout,password,database);
    }
}
