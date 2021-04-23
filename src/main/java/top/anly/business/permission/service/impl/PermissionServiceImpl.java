package top.anly.business.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.anly.business.permission.dao.PermissionDao;
import top.anly.business.permission.domain.Permission;
import top.anly.business.permission.service.PermissionService;
import top.anly.business.relation.domain.RolePermission;
import top.anly.business.relation.domain.UserRole;
import top.anly.business.relation.service.RolePermissionService;
import top.anly.business.relation.service.UserRoleService;
import top.anly.common.param.IdParam;
import top.anly.exception.AppError;
import top.anly.exception.AppException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 权限基本信息SERVICE层接口实现类
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public void batchRemoveByIds(List<IdParam> params) {
        List<Integer> ids = params.stream().map(IdParam::getId).collect(Collectors.toList());
        if (!removeByIds(ids)) {
            throw new AppException(AppError.FAILED);
        }
    }

    @Override
    public List<Permission> getPermissionByUser(Integer userId) {
        List<Permission> permissionList = new ArrayList<>();
        // 查询用户所有角色
        List<UserRole> roleList = userRoleService.lambdaQuery()
                .eq(UserRole::getUid, userId)
                .list();
        if(CollectionUtils.isEmpty(roleList)){
            return permissionList;
        }
        // 查询角色所拥有的权限
        List<Integer> roleIdList = roleList.stream().map(UserRole::getRid).collect(Collectors.toList());
        List<RolePermission> rolePermissionList = rolePermissionService.lambdaQuery()
                .in(RolePermission::getRid, roleIdList)
                .list();
        if(CollectionUtils.isEmpty(rolePermissionList)){
            return permissionList;
        }
        List<Integer> permissionIdList = rolePermissionList.stream().map(RolePermission::getPid).collect(Collectors.toList());
        // 根据权限id列表查询权限信息
        permissionList = lambdaQuery()
                .in(Permission::getId, permissionIdList)
                .list();
        return permissionList;
    }


}
