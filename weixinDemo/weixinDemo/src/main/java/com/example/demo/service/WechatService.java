package com.example.demo.service;

import com.example.demo.component.wechat.AccessTokenModel;
import com.example.demo.component.wechat.ImgTextNewsModel;
import com.example.demo.component.wechat.Wechat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.util.List;

@Service
public class WechatService {
    private static Logger logger = LoggerFactory.getLogger(WechatService.class);
    @Autowired
    private Wechat wechat;
    @Autowired
    private RedisService redisService;

    private static String ACCESS_TOKEN_KEY = "DEMO:WECHAT_ACCESS_TOKEN";

    public String getToken(){
        //先获取redis里保存的token
        Jedis jedis = this.redisService.getJedis();
        if (jedis.exists(ACCESS_TOKEN_KEY)){
            String token = jedis.get(ACCESS_TOKEN_KEY);
            this.redisService.returnJedis(jedis);
            return token;
        }else{
            AccessTokenModel accessTokenModel = this.wechat.getAccessToken();
            if (accessTokenModel != null){
                String res = jedis.setex(ACCESS_TOKEN_KEY,accessTokenModel.getExpires_in(),accessTokenModel.getAccess_token());
                if ("OK".equals(res)){
                    this.redisService.returnJedis(jedis);
                    return accessTokenModel.getAccess_token();
                }else{
                    logger.error("微信access_token缓存到redis失败");
                    return accessTokenModel.getAccess_token();
                }
            }else{
                logger.error("微信access_token获取失败");
                return null;
            }
        }
    }


    public boolean upImgTextNews(List<ImgTextNewsModel> articles){
        return this.wechat.upImgTextNews(this.getToken(),articles);
    }

    public boolean upFileMaterial(File file){
        return this.wechat.upFileMaterial(this.getToken(),file);
    }

    public boolean msgSendAll(String mediaId){
        return this.wechat.msgSendall(this.getToken(),mediaId);
    }
}
