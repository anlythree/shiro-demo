package top.anly.business.relation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.anly.common.param.IdParam;
import top.anly.common.util.Result;
import java.util.List;
import top.anly.business.relation.domain.UserRole;
import top.anly.business.relation.controller.param.UserRoleAddParam;
import top.anly.business.relation.controller.param.UserRoleModifyParam;
import top.anly.business.relation.controller.param.UserRoleListParam;
import top.anly.business.relation.controller.param.UserRoleListPageParam;
import top.anly.business.relation.controller.res.UserRoleRes;


/**
 * 用户_角色SERVICE层接口
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
public interface UserRoleService extends IService<UserRole> {

	/**
	 * 批量删除
	 * @param params
	 */
	void batchRemoveByIds(List<IdParam> params);


}
