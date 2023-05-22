package com.lagou.controller;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色&条件查询
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {

        List<Role> allRole = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "角色列表条件组合查询成功", allRole);

        return responseResult;
    }

    @Autowired
    private MenuService menuService;
    /**
     * 查询所有菜单列表
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {

        // -1 是所有顶层菜单的父菜单ID，因此查询的是所有菜单列表
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        // 根据接口文档的要求，封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);

        // 响应
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单列表成功", map);

        return responseResult;
    }

    /**
     * 根据角色ID查询关联的菜单信息（菜单ID）
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuBuRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据角色ID查询关联菜单信息成功", menuByRoleId);

        return responseResult;
    }

    /**
     * 为角色分配菜单列表
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO) {

        roleService.roleContextMenu(roleMenuVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "为角色分配菜单列表成功", null);

        return responseResult;
    }

    /**
     * 根据角色ID删除对应角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {

        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);

        return responseResult;
    }

}
