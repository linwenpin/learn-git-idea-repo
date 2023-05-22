package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    /**
     * 资源分页&多条件组合查询
     */
    public List<Resource> findAllResource(ResourceVO resourceVO);
}
