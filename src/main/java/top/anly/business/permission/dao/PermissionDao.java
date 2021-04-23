package top.anly.business.permission.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.anly.business.permission.domain.Permission;


/**
 * 权限基本信息DAO层接口
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@Repository
public interface PermissionDao extends BaseMapper<Permission> {
}
