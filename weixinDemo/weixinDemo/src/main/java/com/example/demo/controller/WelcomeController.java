package com.example.demo.controller;

import com.example.demo.component.wechat.ImgTextNewsModel;
import com.example.demo.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WelcomeController {

    @Autowired
    private WechatService wechatService;

    @GetMapping("/")
    public String index(){
        return "hello";
    }


    @GetMapping("/upnews")
    public String upImgTextNews(@RequestParam String mediaId){
        List<ImgTextNewsModel> articles = new ArrayList<>();
        ImgTextNewsModel imgTextNewsModel = new ImgTextNewsModel();
        imgTextNewsModel.setAuthor("伟东");
        imgTextNewsModel.setContent("测试");
        imgTextNewsModel.setContent_source_url("http://www.baidu.com");
        imgTextNewsModel.setDigest("图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。");
        imgTextNewsModel.setShow_cover_pic(0);
        imgTextNewsModel.setThumb_media_id(mediaId);
        articles.add(imgTextNewsModel);
        boolean r = this.wechatService.upImgTextNews(articles);
        return "上传"+(r?"成功":"失败");
    }

    @GetMapping("/upFile")
    public String upFile(){
        File file = new File("F:/1.jpg");
        this.wechatService.upFileMaterial(file);
        return "查看log";
    }

    @GetMapping("/msg-send-all")
    public String msgSendAll(@RequestParam String mediaId){
        this.wechatService.msgSendAll(mediaId);
        return "查看log";
    }
}
