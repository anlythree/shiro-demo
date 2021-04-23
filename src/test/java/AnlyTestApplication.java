import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.anly.ShiroDemoApplication;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;
import top.anly.common.util.generator.ConvertInfo;
import top.anly.common.util.generator.OrmBiz;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangli
 * @date 2021/2/24 18:47
 */
@Slf4j
@RunWith(SpringRunner.class)
@MapperScan("top.anly.*.dao")
@SpringBootTest(classes = ShiroDemoApplication.class)
public class AnlyTestApplication {

    @Autowired
    private OrmBiz ormBiz;

    @Autowired
    private UserService userService;

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    /**
     * 初始化
     */
    @Before
    public void init() {
        simpleAccountRealm.addAccount("anly", "123", "admin", "user");
    }

    /**
     * 测试1
     */
    @Test
    public void test1() {
        // 初始化securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        init();
        // 登录需要一个token，这里可以直接new一个
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("anly", "123");
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        System.out.println("是否登录:" + subject.isAuthenticated());
        System.out.println("是否有admin角色" + subject.hasRole("admin"));
    }

    @Test
    public void generator() {
        List<ConvertInfo> infoList = new ArrayList<>();
        ConvertInfo convertInfo = new ConvertInfo();
        convertInfo.setTableName("permission");
        convertInfo.setAuthor("anlythree");
        convertInfo.setPackName("permission");
        convertInfo.setContent("权限基本信息");
        convertInfo.setSavePath("F:\\my\\shiro-demo");
        infoList.add(convertInfo);
        ormBiz.convert(infoList);
        log.info("代码生成成功!");
    }

    @Test
    public void testUser(){
        List<User> list = userService.list();
        System.out.println(list);
    }


}
