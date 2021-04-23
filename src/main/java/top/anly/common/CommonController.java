package top.anly.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;
import top.anly.common.util.Result;
import top.anly.common.util.generator.ConvertInfo;
import top.anly.common.util.generator.OrmBiz;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共模块
 *
 * @author yinhaijing
 * @date 2019-05-08
 */
@Api(tags = "公共模块")
@Slf4j
@RequestMapping(produces = "application/json;charset=utf-8", value = "/common", method = RequestMethod.POST)
@RestController
public class CommonController {

    private int a = 0;

    @Autowired
    private OrmBiz ormbiz;

    @Autowired
    private UserService userService;

    @ApiOperation("代码生成-私有")
    @RequestMapping("/generate")
    public Result generate(@RequestBody ConvertInfo convertInfo) {
        List<ConvertInfo> infoList = new ArrayList<>();
        if(null == convertInfo || StringUtils.isEmpty(convertInfo.getTableName())){
            convertInfo.setTableName("permission");
            convertInfo.setAuthor("anlythree");
            convertInfo.setPackName("permission");
            convertInfo.setContent("权限基本信息");
            convertInfo.setSavePath("F:\\my\\shiro-demo");
        }
        infoList.add(convertInfo);
        ormbiz.convert(infoList);
        log.info("代码生成成功!");
        return Result.ok();
    }


    @ApiOperation("测试连接数据库")
    @RequestMapping("/testUserList")
    public Result testUserList() {
        List<User> list = userService.lambdaQuery().list();
        System.out.println(list);
        return Result.ok(list);
    }

}
