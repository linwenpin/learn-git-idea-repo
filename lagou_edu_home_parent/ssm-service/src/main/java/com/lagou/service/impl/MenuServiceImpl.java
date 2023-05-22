package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    // 根据父菜单ID查询所有子孙菜单
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {

        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);
        return menuList;
    }

    // 查询所有菜单信息
    @Override
    public List<Menu> findAllMenu() {

        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    // 根据菜单ID查询菜单
    @Override
    public Menu findMenuById(Integer id) {

        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }
}
