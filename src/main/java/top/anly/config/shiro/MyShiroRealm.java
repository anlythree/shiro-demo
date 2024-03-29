package top.anly.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.anly.business.permission.domain.Permission;
import top.anly.business.permission.service.PermissionService;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;
import top.anly.common.util.CommonUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wangli
 * @date 2021/4/23 14:24
 */
@Slf4j
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 认证、登录逻辑
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo");
        // 获取当前用户名
        String account = (String) token.getPrincipal();
        // 查询数据库
        User user = userService.getUserByName(account);
        if (null == user) {
            // 如果shiro的认证逻辑返回了null就说明认证或登录失败
            return null;
        }
        // 构造返回值
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassWord(),
                // 盐值
                ByteSource.Util.bytes(user.getSale()),
                this.getName()
        );
        return authenticationInfo;
    }
    //认证访法
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("realm------------------------");
//        UsernamePasswordToken mytoken=(UsernamePasswordToken) token;
//        String account=mytoken.getUsername();
//        //根据用户名查询数据·库中的密码
//        User user = userService.getUserByName(account);
//        if(user==null) {
//            //用户不存在
//            return null;
//        }
//        //如果能查询到，再由框架比对数据库中查询到的密码和页面提交的密码是否一致
//        AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassWord(),this.getName());
//        return info;
//    }


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo");
        User user = (User) principals.getPrimaryPrincipal();
        List<Permission> permissionListByUser = permissionService.getPermissionByUser(user.getId());
        List<String> permCodeList = permissionListByUser.stream().map(Permission::getPermCode).collect(Collectors.toList());
        Set<String> permCodeSet = CommonUtils.ListToSet(permCodeList);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permCodeSet);
        return simpleAuthorizationInfo;
    }
}
