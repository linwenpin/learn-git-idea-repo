package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    // 询所有角色&条件查询
    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    // 根据角色ID查询关联的菜单信息（菜单ID）
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    // 为角色分配菜单列表
    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        int roleId = roleMenuVO.getRoleId();

        // 根据角色ID清空中间表中对应的关联关系
        roleMapper.deleteRoleContextMenu(roleId);

        // 遍历menuIdList
        for (Integer menuId : roleMenuVO.getMenuIdList()) {
            // 封装数据，补全信息
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleId);
            role_menu_relation.setMenuId(menuId);

            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            // 调用Mapper，添加新的关联关系
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    // 根据角色ID删除对应角色
    @Override
    public void deleteRole(Integer roleId) {

        // 先根据角色ID清空中间表中对应的关联关系
        roleMapper.deleteRoleContextMenu(roleId);

        // 再根据角色ID删除角色表中对应的记录
        roleMapper.deleteRole(roleId);
    }

}
