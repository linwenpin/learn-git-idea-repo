package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {

        List<Menu> allMenu = menuService.findAllMenu();

        return new ResponseResult(true, 200, "查询所有菜单信息成功", allMenu);
    }

    /**
     * 查询菜单信息(回显)
     *  1）对于添加，请求参数id=-1
     *  2）对于修改，请求参数id=菜单ID
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {

        // 根据参数id判断是添加菜单还是修改菜单
        if (id == -1) {
            // 添加菜单（回显）
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            // 封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", subMenuListByPid);
            // 响应
            return new ResponseResult(true, 200, "添加菜单回显成功", map);
        } else {
            // 修改菜单（回显）
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            Menu menu = menuService.findMenuById(id);
            // 封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", subMenuListByPid);
            // 响应
            return new ResponseResult(true, 200, "修改菜单回显成功", map);
        }
    }

}
