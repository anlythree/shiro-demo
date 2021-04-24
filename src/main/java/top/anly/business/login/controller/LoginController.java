package top.anly.business.login.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.anly.business.login.controller.param.LoginParam;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;
import top.anly.common.util.Result;

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
        User user = userService.lambdaQuery()
                .eq(User::getAccount, loginParam.getAccount())
                .last("limit 1;")
                .one();
        if (null == user) {
            user = new User();
            user.setAccount("anly");
            user.setEmail("2450@qq.com");
        }
        return Result.ok(user);
    }

    @ApiOperation("登录接口2")
    @RequestMapping(value = "/doLogin2")
    public Result<User> doLogin2(@RequestBody LoginParam loginParam) {
        User user = userService.lambdaQuery()
                .eq(User::getAccount, loginParam.getAccount())
                .last("limit 1;")
                .one();
        if (null == user) {
            user = new User();
            user.setAccount("anly");
            user.setEmail("2450@qq.com");
        }
        return Result.ok(user);
    }
}
