package top.anly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author wangli
 * @date 2020/9/14 14:59
 */
@SpringBootApplication
@EnableRedisHttpSession
public class ShiroDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDemoApplication.class,args);
        System.out.println("shrio测试项目启动成功！！！");
    }

}
