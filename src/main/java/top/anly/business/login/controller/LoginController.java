package top.anly.business.login.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.anly.business.login.controller.param.LoginParam;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;
import top.anly.common.util.Result;
import top.anly.exception.AppError;
import top.anly.exception.AppException;

/**
 * @author wangli
 * @date 2021/4/24 13:55
 */
@Slf4j
@RestController
@RequestMapping(value = "/login/login", method = RequestMethod.POST)
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录接口")
    @RequestMapping(value = "/doLogin")
    public Result<User> doLogin(@RequestBody LoginParam loginParam) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                loginParam.getAccount(),
                loginParam.getPsWd()
        );
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            String simpleName = e.getClass().getSimpleName();
            if ("UnknownAccountException".equals(simpleName)) {
                throw new AppException(AppError.QUERY_FAILED, "用户名不存在");
            } else if ("IncorrectCredentialsException".equals(simpleName)) {
                throw new AppException(AppError.QUERY_FAILED, "密码错误");
            }
        }
        User userByName = userService.getUserByName(loginParam.getAccount());
        if(subject.isAuthenticated()){
            // 登录成功
            return Result.ok(userByName);
        }else {
            // 登录失败
            return Result.ok();
        }
    }
}
