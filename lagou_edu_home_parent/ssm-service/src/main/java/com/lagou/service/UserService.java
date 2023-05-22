package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 用户分页&多条件查询：根据用户名称（手机号）和注册时间进行组合查询
     * @return
     */
    public PageInfo<User> findAllUserByPage(UserVO userVO);

    /**
     * 登录：根据用户名（实际是手机号）查询用户信息
     */
    public User login(User user) throws Exception;

    /**
     * 分配角色（回显）：根据用户ID查询与该用户关联的所有角色
     */
    public List<Role> findUserRoleById(Integer userId);

    /**
     * 分配角色：先清空相关的关联关系，再添加新的关联关系
     */
    public void userContextRole(UserVO userVO);

    /**
     * 动态菜单显示：获取用户权限
     */
    public Map<String, Object> getUserPermissions(Integer userId);
}
