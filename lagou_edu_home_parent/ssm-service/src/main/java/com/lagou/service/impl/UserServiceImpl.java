package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 用户分页&多条件查询：根据用户名称（手机号）和注册时间进行组合查询
    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {

        // 设置分页查询的当前页和每页显示条数
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());

        // 调用Mapper
        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);

        // 获取PageInfo，它封装了分页的详细信息
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);

        return pageInfo;
    }

    // 登录：根据用户名（实际是手机号）查询用户信息
    @Override
    public User login(User user) throws Exception {

        User login = userMapper.login(user);

        // 判断用户是否已注册且密码是否匹配
        if (login != null && Md5.verify(user.getPassword(), "lagou", login.getPassword())) {
            // 用户已注册且密码匹配
            return login;
        } else {
            // 用户未注册或密码不匹配
            return null;
        }
    }

    // 分配角色（回显）根据用户ID查找与该用户关联的所有角色信息
    @Override
    public List<Role> findUserRoleById(Integer userId) {

        List<Role> roles = userMapper.findUserRoleById(userId);
        return roles;
    }

    // 分配角色：先清空相关的关联关系，再添加新的关联关系
    @Override
    public void userContextRole(UserVO userVO) {

        // 先清空中间表中相关的关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        // 遍历 roleIdList
        for (Integer roleId : userVO.getRoleIdList()) {
            // 封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            // 调用mapper添加新的关联关系
            userMapper.userContextRole(user_role_relation);
        }
    }

    // 动态菜单显示：获取用户权限
    @Override
    public Map<String, Object> getUserPermissions(Integer userId) {

        // 1. 根据用户ID获取该用户的所有角色
        List<Role> roleList = userMapper.findUserRoleById(userId);

        // 2. 获取角色ID并封装到List集合中
        List<Integer> roleIdList = roleList.stream().map(Role::getId).collect(Collectors.toList());

        // 3. 根据角色ID集合，查询关联的所有顶级菜单
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleIds(roleIdList);

        // 4. 根据顶级菜单ID，查询它的所有子级菜单
        for (Menu parentMenu : parentMenuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByPid(parentMenu.getId());
            parentMenu.setSubMenuList(subMenuList);
        }

        // 5. 根据角色ID集合，查询关联的所有资源
        List<Resource> resourceList = userMapper.findResourceByRoleIds(roleIdList);

        // 6. 根据接口文档封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenuList);
        map.put("resourceList", resourceList);

        return map;
    }

}
