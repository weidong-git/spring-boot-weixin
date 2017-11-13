package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取redis字符串
     * @param key
     * @return
     */
    public String getString(String key){
        Jedis jedis = this.getJedis();
        if (jedis.exists(key)){
            String value =  jedis.get(key);
            this.returnJedis(jedis);
            return value;
        }else{
            this.returnJedis(jedis);
            return null;
        }
    }


    public synchronized Jedis getJedis(){
        return this.jedisPool.getResource();
    }

    public synchronized void returnJedis(Jedis jedis){
        jedis.close();
    }


}
