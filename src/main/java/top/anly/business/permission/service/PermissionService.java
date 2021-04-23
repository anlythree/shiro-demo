package top.anly.business.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.anly.business.permission.domain.Permission;
import top.anly.common.param.IdParam;

import java.util.List;


/**
 * 权限基本信息SERVICE层接口
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 批量删除
     *
     * @param params
     */
    void batchRemoveByIds(List<IdParam> params);

    /**
     * 根据用户id查询该用户所有的权限列表
     * @param userId
     * @return
     */
    List<Permission> getPermissionByUser(Integer userId);


}
