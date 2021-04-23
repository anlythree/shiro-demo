package top.anly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author
 * @date
 */
@EnableTransactionManagement
@MapperScan("top.anly.business.*.dao")
@SpringBootApplication
@EnableAspectJAutoProxy
public class ShiroDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(top.anly.ShiroDemoApplication.class, args);
    }
}
