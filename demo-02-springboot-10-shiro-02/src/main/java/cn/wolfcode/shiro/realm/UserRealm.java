package cn.wolfcode.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wolfcode-lanxw
 */
@Component
public class UserRealm extends AuthorizingRealm {
    //模拟数据库用户的登陆数据
    public static Map<String,String> userData = new HashMap<String,String>();
    //模拟数据库中用户的权限数据
    public static Map<String,List<String>> permissionData = new HashMap<String,List<String>>();
    static{
        userData.put("zhangsan","666");
        userData.put("lisi","888");
        //zhangsan拥有员工页面和部门页面的访问权限
        List<String> p1 = new ArrayList<String>();
        p1.add("employee:page");
        p1.add("department:page");
        permissionData.put("zhangsan",p1);
        //lisi只拥有部门页面的访问全新
        List<String> p2 = new ArrayList<String>();
        p2.add("department:page");
        permissionData.put("lisi",p2);
    }
    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登陆的信息
        String userName = (String) principals.getPrimaryPrincipal();
        //获取登陆用户的权限信息
        List<String> perms = permissionData.get(userName);
        //创建授权信息对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //关联用户的权限信息
        info.addStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         * 降低学习的难度,所以这块我就没有去集成MyBatis.
         * 直接用的是静态的数据.
         */
        String username = (String) token.getPrincipal();
        String password = userData.get(username);
        if(password==null){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
        return info;
    }
}
