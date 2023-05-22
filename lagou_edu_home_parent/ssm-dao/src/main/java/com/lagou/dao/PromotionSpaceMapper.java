package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    /**
     * 查询所有广告位
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 新增广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 回显广告位名称：根据ID查询广告位信息
     */
    public PromotionSpace findPromotionSpaceById(Integer id);

    /**
     * 更新广告位名称
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
