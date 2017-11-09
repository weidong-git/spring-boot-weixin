package com.example.demo.component.wechat;

import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Wechat {

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;

    //获取access_token
    @SuppressWarnings({"unchecked"})
    public AccessTokenModel getAccessToken(){
        AccessTokenModel accessTokenModel = new AccessTokenModel();
        String response = HttpClientUtil.httpGetRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+this.appId+"&secret="+this.appSecret);
        if (StringUtils.isEmpty(response)){
            accessTokenModel.setSuccess(false);
            accessTokenModel.setMsg("连接微信服务器出错");
            System.out.println(accessTokenModel);
            return accessTokenModel;
        }else {
            Map<String,Object> respMap;
            try {
                 respMap = JsonUtil.decode(response,HashMap.class);
            }catch (IOException e){
                accessTokenModel.setSuccess(false);
                accessTokenModel.setMsg("微信响应消息解析出错");
                return accessTokenModel;
            }

            if (respMap.containsKey("access_token")){
                System.out.println(respMap.get("access_token"));
            }else{
                System.out.println(respMap.get("access_token"));
            }
            return null;
        }
    }
}
