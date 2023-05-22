package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

public interface PromotionAdService {

    /**
     * 分页获取所有广告
     * @return
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);


    /**
     * 广告动态上下线: 根据ID修改对应广告的状态
     */
    public void updatePromotionAdStatus(Integer id, Integer status);

}
