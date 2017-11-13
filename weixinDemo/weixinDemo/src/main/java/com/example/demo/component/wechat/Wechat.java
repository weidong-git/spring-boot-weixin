package com.example.demo.component.wechat;

import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Wechat {

    private static Logger logger = LoggerFactory.getLogger(Wechat.class);

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;

    //获取access_token
    public AccessTokenModel getAccessToken(){
        String response = HttpClientUtil.httpGetRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+this.appId+"&secret="+this.appSecret);
        if (!StringUtils.isEmpty(response)){
            try {
                Map respJson = JsonUtil.decode(response,Map.class);
                if (respJson.containsKey("access_token")){
                    AccessTokenModel accessTokenModel = new AccessTokenModel();
                    accessTokenModel.setAccess_token((String) respJson.get("access_token"));
                    accessTokenModel.setExpires_in((Integer)respJson.get("expires_in"));
                    return accessTokenModel;
                }else{
                    logger.error("获取微信access_token失败，返回信息{}",respJson);
                    return null;
                }
            }catch (IOException e){
                e.printStackTrace();
                logger.error("获取微信access_token失败，返回信息{}",response);
                return null;
            }
        }else {
            logger.error("获取微信access_token出错，微信服务器响应信息为空或请求异常");
            return null;
        }
    }

    /**
     * 上传图文消息
     * @return
     */
    public boolean upImgTextNews(String token,List<ImgTextNewsModel> articles){
        Map<String,List<ImgTextNewsModel>> params = new HashMap<>();
        params.put("articles",articles);
        String json = null;
        try {
            json = JsonUtil.encode(params);
        }catch (JsonProcessingException e){
            logger.error("上传图文消息失败，转换json消息失败,错误信息{}",e.getMessage());
            return false;
        }
        String resp = HttpClientUtil.httpJsonPostRequest("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="+token,json);
        System.out.println("响应信息："+resp);
        return false;
    }

    /**
     * 上传文件素材
     * @return
     */
    public boolean upFileMaterial(String accessToken,File file){
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("media", file);
        String res = HttpClientUtil.httpMultipartPostRequest("https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type=thumb",multipartEntityBuilder);
        System.out.println("上传结果="+res);
        return false;
    }


    public boolean msgSendall(String accessToken,String mediaId){
        Map<String,Object> jsonObj = new HashMap<>();
        String json = "{\"filter\":{\"is_to_all\":true},\"mpnews\":{\"media_id\":\""+mediaId+"\"},\"msgtype\":\"mpnews\",\"send_ignore_reprint\":0}";
        String res = HttpClientUtil.httpJsonPostRequest("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+accessToken,json);
        System.out.println("群发结果="+res);
        return false;
    }
}
