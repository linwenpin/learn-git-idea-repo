package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色&条件查询
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询关联的菜单信息（菜单ID）
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 为角色分配菜单列表
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /**
     * 根据角色ID删除对应角色
     */
    public void deleteRole(Integer roleId);

}
