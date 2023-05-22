package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import com.lagou.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {

        // 调用Service
        PageInfo<PromotionAd> allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        // 把分页查询结果封装在ResponseResult中，转换为JSON，进行响应
        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", allPromotionAdByPage);

        return responseResult;
    }

    /**
     * 广告图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult promotionAdUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) {

        try {
            Map<String, String> map = FileUploadUtils.upload(file, request);

            ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

            return responseResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 广告动态上下线: 根据ID修改对应广告的状态
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id, Integer status) {

        // 调用Service层
        promotionAdService.updatePromotionAdStatus(id, status);

        // 封装并返回
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true, 200, "广告动态上下线成功", map);
    }
}
