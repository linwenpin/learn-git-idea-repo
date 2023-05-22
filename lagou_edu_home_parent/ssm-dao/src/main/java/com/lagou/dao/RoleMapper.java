package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有角色&条件查询
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询关联的菜单信息（菜单ID）
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 根据角色ID清空中间表中的关联关系
     */
    public void deleteRoleContextMenu(Integer roleId);

    /**
     * 向中间表添加关联关系
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 根据角色ID删除对应角色
     */
    public void deleteRole(Integer roleId);
}
