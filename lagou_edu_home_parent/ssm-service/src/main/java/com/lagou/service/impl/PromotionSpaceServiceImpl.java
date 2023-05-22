package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    // 查询所有广告位
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.findAllPromotionSpace();

        return allPromotionSpace;
    }

    // 新增广告位
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        // 补全信息
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        // 调用DAO层保存广告位信息
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    // 回显广告位名称：根据ID查询广告位信息
    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {

        return promotionSpaceMapper.findPromotionSpaceById(id);
    }

    // 更新广告位名称
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        // 补全信息
        promotionSpace.setUpdateTime(new Date());

        // 调用mapper
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
