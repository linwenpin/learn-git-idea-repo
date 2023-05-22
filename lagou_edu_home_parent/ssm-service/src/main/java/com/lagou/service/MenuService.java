package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 根据父菜单ID查询该父菜单的所有子菜单以及子菜单的所有子菜单（要保持这种结构）
     * （分配菜单的第一个接口）
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询所有菜单信息
     */
    public List<Menu> findAllMenu();

    /**
     * 根据菜单ID查询菜单信息
     */
    public Menu findMenuById(Integer id);
}
