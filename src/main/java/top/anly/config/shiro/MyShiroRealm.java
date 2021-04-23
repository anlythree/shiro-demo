package top.anly.config.shiro;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;

/**
 * @author wangli
 * @date 2021/4/23 14:24
 */
@Slf4j
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 认证、登录逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo");
        // 获取当前用户名
        String userName = (String)token.getPrincipal();
        // 查询数据库
        User user = userService.lambdaQuery()
                .eq(User::getUserName, userName)
                .last("limit 1;")
                .one();
        if(null == user){
            // 如果shiro的认证逻辑返回了null就说明认证或登录失败
            return null;
        }
        // 构造返回值
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                userName,
                // 盐值
                ByteSource.Util.bytes(user.getSale()),
                "MyShiroRealm"
        );
        return authenticationInfo;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo");
        return null;
    }
}
