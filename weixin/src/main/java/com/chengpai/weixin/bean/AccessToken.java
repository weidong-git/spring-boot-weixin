package com.chengpai.weixin.bean;

public class AccessToken {
    //获取到的凭证
    private String access_token;
    //凭证有效时间，单位：秒
    private String token_time;

    public AccessToken(String access_token, String token_time) {
        this.access_token = access_token;
        this.token_time = token_time;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_time() {
        return token_time;
    }

    public void setToken_time(String token_time) {
        this.token_time = token_time;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "access_token='" + access_token + '\'' +
                ", token_time='" + token_time + '\'' +
                '}';
    }
}
