package top.anly.business.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.anly.business.permission.dao.PermissionDao;
import top.anly.business.permission.domain.Permission;
import top.anly.business.permission.service.PermissionService;
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
    private PermissionService permissionService;

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
        // 查询权限id集合
//        lambdaQuery()
//                .eq(Permission::)
        return permissionList;
    }


}
