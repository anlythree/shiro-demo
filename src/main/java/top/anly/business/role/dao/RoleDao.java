package top.anly.business.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.anly.business.role.domain.Role;


/**
 * 角色基本信息DAO层接口
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@Repository
public interface RoleDao extends BaseMapper<Role> {
}
