package top.anly.business.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.anly.business.user.dao.UserDao;
import top.anly.business.user.domain.User;
import top.anly.business.user.service.UserService;

/**
 * @author wangli
 * @date 2021/4/11 14:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService{

    @Override
    public User getUserByName(String name) {
        User user = lambdaQuery()
                .eq(User::getAccount, name)
                .last("limit 1;")
                .one();
        return user;
    }
}
