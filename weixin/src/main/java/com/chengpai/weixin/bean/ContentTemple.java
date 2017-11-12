package com.chengpai.weixin.bean;

public class ContentTemple {
    /*接收者openid*/
    private String touser = null;
    /**/
    private String msgtype = null;
    /*发送内容*/
    private String content = null;

    public ContentTemple(String touser){
        this.touser = touser;
    }

    public String getStr(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" { ");
        stringBuffer.append("     \"touser\":\""+touser+"\", ");
        stringBuffer.append("     \"msgtype\":\"text\", ");
        stringBuffer.append("     \"text\": ");
        stringBuffer.append("     { ");
        stringBuffer.append("          \"content\":\""+content+"\" ");
        stringBuffer.append("     } ");
        stringBuffer.append(" } ");
        return stringBuffer.toString();
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
