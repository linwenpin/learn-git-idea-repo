package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户分页&多条件组合查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {

        // 调用Service层
        PageInfo<User> allUserByPage = userService.findAllUserByPage(userVO);

        // 封装结果，进行响应
        ResponseResult responseResult = new ResponseResult(true, 200, "用户分页条件组合查询成功", allUserByPage);

        return responseResult;
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        // 调用Service进行认证
        User login = userService.login(user);

        // 判断是否通过认证
        if (login != null) {
            // 通过认证
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            Map<String, Object> map = new HashMap<>();

            // 把token和id存入session
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", login.getId());

            // 把toke和id存入map，准备发送给前端
            map.put("access_token", access_token);
            map.put("user_id", login.getId());

            // 把用户信息存入map，准备发送给前端，为登出功能做准备
            map.put("user", login);

            // 响应
            return new ResponseResult(true, 1, "登录成功", map);
        } else {
            // 未通过认证
            return new ResponseResult(false, 400, "用户名或密码错误", null);
        }
    }

    /**
     * 分配角色（回显）：根据用户ID查询与该用户关联的所有角色
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id) {

        List<Role> userRoleList = userService.findUserRoleById(id);

        return new ResponseResult(true, 200, "分配角色回显成功", userRoleList);
    }

    /**
     * 分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO) {

        userService.userContextRole(userVO);

        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    /**
     * 动态菜单显示：获取用户权限
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        // 1. 从请求头中获取token
        String header_token = request.getHeader("Authorization");

        // 2. 从session中获取token
        String session_token = (String) request.getSession().getAttribute("access_token");

        // 3. 判断两个token是否一致
        if (header_token.equals(session_token)) {
            // 4. 如果一致，则 1）从session中取出user_id; 2) 调用Service获取用户权限；3）响应
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            Map<String, Object> userPermissions = userService.getUserPermissions(user_id);

            return new ResponseResult(true, 200, "获取用户权限成功", userPermissions);
        } else {
            // 5. 如果不一致，响应错误信息
            return new ResponseResult(false, 400, "获取用户权限失败", null);
        }
    }

}
