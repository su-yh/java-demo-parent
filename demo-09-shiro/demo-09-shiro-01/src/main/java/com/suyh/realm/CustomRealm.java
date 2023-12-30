package com.suyh.realm;

import com.suyh.entity.Permissions;
import com.suyh.entity.Role;
import com.suyh.entity.User;
import com.suyh.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权...");

        // 从session 中获取到 user 属性对象
         User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");

        // 使用下面的方法进行获取用户名有危险，不一定就取到的就是用户名。
        // 网上说的它是获取一个列表中的下一个元素。
        // 获取登录用户名
//        String name = (String) principalCollection.getPrimaryPrincipal();
//        // 根据用户名去数据库查询用户信息
//        User user = loginService.getUserByName(name);

        // 添加角色和权限，交给shiro 管理
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            // 添加权限
            for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     *
     * 认证检测(用户身份是否存在，密码是否正确)
     * 调用流程: subject.login(token) --> securityManager --> Authentication
     *              --> Realm.doGetAuthenticationInfo
     * 对应创建用户时的密码加密流程以及方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("认证...");

        // UsernamePasswordToken 是在 subject.login(token) 传入的类型
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        User user = loginService.getUserByName(username);
        if (user == null) {
            // 这里返回后会报出对应异常
            return null;
        }

        ByteSource byteSource = ByteSource.Util.bytes(user.getSalt().getBytes());

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                // 用户身份，已加密的密码，盐值对应的ByteSource, realm 的名字
                user.getUserName(), user.getPassword(), byteSource, getName());

        // 将用户信息存储到session 中，或者在实际生产中应该保存到redis 中
        SecurityUtils.getSubject().getSession().setAttribute("user", user);

        return info;	// 此对象返回给认证了？ 认证器对象
    }
}
