package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
     * 分页获取所有广告
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /**
     * 广告动态上下线
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
