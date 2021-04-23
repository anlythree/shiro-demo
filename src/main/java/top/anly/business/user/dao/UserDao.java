package top.anly.business.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.anly.business.user.domain.User;

/**
 * @author wangli
 * @date 2021/4/11 14:42
 */
@Repository
public interface UserDao extends BaseMapper<User> {
}
