package top.anly.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.anly.business.user.domain.User;

/**
 * @author wangli
 * @date 2021/4/11 14:39
 */
public interface UserService extends IService<User> {

    /**
     * 通过名称查找用户
     * @param name
     * @return
     */
    User getUserByName(String name);


}
