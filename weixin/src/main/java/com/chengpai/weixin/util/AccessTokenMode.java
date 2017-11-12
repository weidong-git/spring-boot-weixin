package com.chengpai.weixin.util;

import com.chengpai.weixin.bean.AccessToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccessTokenMode {

    @Value(value ="${weixin.appId}")
    private static String APPID;

    @Value(value = "${weixin.appSecret}")
    private static String SECRET;
    /**
     * 获取token
     */
    public static AccessToken getAccessToken(){
        /*返回的accessToken*/
        AccessToken accessToken = null;
        /*得到的网络信息*/
        StringBuffer stringBuffer = new StringBuffer();

        HttpURLConnection httpURLConnection = null;
        System.out.println(APPID);
        System.out.println(SECRET);

        //这是微信得到url_AccessToken所需的url
        String url_AccessToken ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WX_Info.APPID+"&secret="+WX_Info.SECRET;

        try {
            //得到AccessToken的URL
            URL url = new URL(url_AccessToken);
            //打开url
            httpURLConnection = (HttpURLConnection)url.openConnection();
            //打开连接
            httpURLConnection.connect();
            //得到InputStream
            InputStream in = httpURLConnection.getInputStream();
            //得到InputStreamReader
            InputStreamReader reader = new InputStreamReader(in);
            //得到BufferedReader
            BufferedReader breader = new BufferedReader(reader);
            String str = null;

			/*读取网络信息*/
            while(null != (str = breader.readLine())){
                stringBuffer.append(str);
            }
            //关闭breader
            breader.close();
            //关闭reader
            reader.close();
            //关闭in
            in.close();
            Gson gson = new Gson();
            accessToken = gson.fromJson(stringBuffer.toString(), AccessToken.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null!=httpURLConnection){
            httpURLConnection.disconnect();
        }
        return accessToken;
    }
}
