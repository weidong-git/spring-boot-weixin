package com.example.demo.component.wechat;

public class AccessTokenModel {

    private boolean success;
    private String msg;
    private String key;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "AccessTokenModel{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
