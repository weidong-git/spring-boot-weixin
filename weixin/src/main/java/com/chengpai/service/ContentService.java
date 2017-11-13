/**
 * Copyright (C), 2017-2017, 1130160475@qq.com
 * FileName: service
 * Author:   ${1130160475@qq.com}
 * Date:     2017/11/13 0013 下午 7:04
 * Description: 消息发送服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chengpai.service;


import com.chengpai.weixin.bean.AccessToken;
import com.chengpai.weixin.util.AccessTokenMode;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class ContentService {

    public static void service(AccessTokenMode accessTokenMode){
          /*得到AccessToken*/
        AccessToken accessToken = AccessTokenMode.getAccessToken();

        try {

			/*得到url*/
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken.getAccess_token());

			/*得到connection*/
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

			/*得到out*/
            OutputStream out = connection.getOutputStream();

			/*得到writer*/
            OutputStreamWriter writer = new OutputStreamWriter(out,"UTF-8");

			/*得到bwriter*/
            BufferedWriter bwriter = new BufferedWriter(writer);

            bwriter.write(accessTokenMode.getAccessToken());

            bwriter.flush();

			/*得到in*/
            InputStream in = connection.getInputStream();

			/*得到reader*/
            InputStreamReader reader = new InputStreamReader(in);

			/*得到breader*/
            BufferedReader breader = new BufferedReader(reader);

            String str = null;

            StringBuffer strb = new StringBuffer();

            while(null !=(str = breader.readLine())){

                strb.append(str);

            }

			/*关闭bwriter*/
            bwriter.close();

			/*关闭writer*/
            writer.close();

			/*关闭out*/
            out.close();



			/*关闭breader*/
            breader.close();

			/*关闭reader*/
            reader.close();

			/*关闭in*/
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}