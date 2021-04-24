import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import top.anly.business.user.domain.User;

/**
 * @author wangli
 * @date 2021/4/24 10:08
 */
@Component
public class SimpleTest {

    @Bean
    public User user(boolean b){
        User user = new User();
        if(b){
            return user;
        }
        user.setAccount("account");
        return user;
    }

}
