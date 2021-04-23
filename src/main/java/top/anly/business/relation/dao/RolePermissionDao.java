package top.anly.business.relation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.anly.business.relation.domain.RolePermission;


/**
 * 角色-权限DAO层接口
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@Repository
public interface RolePermissionDao extends BaseMapper<RolePermission> {
}
