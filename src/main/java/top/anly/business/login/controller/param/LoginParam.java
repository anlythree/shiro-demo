package top.anly.business.login.controller.param;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangli
 * @date 2021/4/24 14:00
 */
@Data
@ApiModel("登录入参")
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {

    private String account;

    private String psWd;

    private String token;

}
