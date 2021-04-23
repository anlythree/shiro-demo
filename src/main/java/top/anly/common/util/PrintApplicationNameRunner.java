package top.anly.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中的spring.application.name并在项目初始化时在控制台打印启动成功信息
 *
 * @author wangli
 * @date 2020/12/23 9:32
 */
@Component
public class PrintApplicationNameRunner implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        applicationName = null == applicationName ? "未配置名称" : applicationName;
        System.out.println(applicationName + "项目启动成功!!!");
    }
}
