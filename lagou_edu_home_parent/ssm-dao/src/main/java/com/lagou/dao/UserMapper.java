package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /**
     * 用户分页&多条件查询：根据用户名称（手机号）和注册时间进行组合查询
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /**
     * 登录：根据用户名（实际是手机号）查询用户信息
     */
    public User login(User user);

    /**
     * 根据角色ID清空中间表中对应的关联关系
     */
    public void deleteUserContextRole(Integer userId);

    /**
     * 分配角色：向中间表添加记录
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /**
     * 分配角色（回显）：根据用户ID查询与该用户关联的所有角色
     * 该方法被 动态菜单显示 复用
     */
    public List<Role> findUserRoleById(Integer userId);

    //-- 动态菜单显示
    /**
     * 根据角色ID集合，查询对应的所有顶级菜单（分步查询1）
     */
    public List<Menu> findParentMenuByRoleIds(List<Integer> roleIds);

    /**
     * 再对顶级菜单关联的子菜单进行关联查询（分步查询2）
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /**
     * 根据角色ID集合，查询对应的所有资源
     */
    public List<Resource> findResourceByRoleIds(List<Integer> roleIds);
}
