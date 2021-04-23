package top.anly.business.relation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.anly.common.param.IdParam;
import top.anly.common.util.Result;
import java.util.List;
import top.anly.business.relation.domain.RolePermission;
import top.anly.business.relation.controller.param.RolePermissionAddParam;
import top.anly.business.relation.controller.param.RolePermissionModifyParam;
import top.anly.business.relation.controller.param.RolePermissionListParam;
import top.anly.business.relation.controller.param.RolePermissionListPageParam;
import top.anly.business.relation.controller.res.RolePermissionRes;


/**
 * 角色-权限SERVICE层接口
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
public interface RolePermissionService extends IService<RolePermission> {

	/**
	 * 批量删除
	 * @param params
	 */
	void batchRemoveByIds(List<IdParam> params);


}
