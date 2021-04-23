package top.anly.common.util.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;

import java.util.*;

/**
 * dao操作类
 * @author wangli
 * @date 2021/3/15 16:15
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 模拟数据库
     */
    Map<String, String> userMap = new HashMap(16);

    {
        userMap.put("anly", "123");
        super.setName("myRealm");
    }

    /**
     * 重载方法
     * 主体传过来的认证信息(获取身份验证信息)
     *
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1、从主体传递过来的认证信息中，获得用户名
        String userName = (String) token.getPrincipal();
        // 2、通过用户名到数据库中获取凭证
        String passWordByUserName = getPassWordByUserName(userName);
        if (null == passWordByUserName) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("anly", passWordByUserName, "myRealm");
        return authenticationInfo;
    }

    /**
     * 通过用户名获取密码
     *
     * @param name
     * @return
     */
    private String getPassWordByUserName(String name) {
        // 这里模拟去数据库中取数据的过程
        if ("anly".equals(name)) {
            return "123";
        }
        return null;
    }


    /**
     * 重载方法
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        // 从数据库中获取角色和权限信息
        Set<String> roleSetByUserName = getRoleSetByUserName(user.getUserName());
        // 从数据库中获取权限信息
        Set<String> permissionSetByUserName = getPermissionSetByUserName(user.getUserName());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionSetByUserName);
        simpleAuthorizationInfo.setRoles(roleSetByUserName);
        return simpleAuthorizationInfo;
    }


    /**
     * 根据用户名查询角色列表
     *
     * @return
     */
    Set<String> getRoleSetByUserName(String userName) {
        // 初始化返回值
        Set<String> roleSet = new TreeSet<String>();
        if ("anly".equals(userName)) {
            roleSet.add("admin");
        }
        roleSet.add("userRole");
        return roleSet;
    }

    /**
     * 根据用户名查询用户权限列表
     *
     * @param userName
     * @return
     */
    Set<String> getPermissionSetByUserName(String userName) {
        // 初始化返回值
        Set<String> permissionSet = new TreeSet<String>();
        if ("anly".equals(userName)) {
            permissionSet.add("add");
            permissionSet.add("update");
            permissionSet.add("delete");
        }
        permissionSet.add("select");
        return permissionSet;
    }

    public void test(){
        List<User> userList = userService.lambdaQuery()
                .eq(User::getAccount, "anly")
                .eq(User::getStatus, 1)
                .select(User::getUserName,User::getEmail)
                .list();
    }
}
