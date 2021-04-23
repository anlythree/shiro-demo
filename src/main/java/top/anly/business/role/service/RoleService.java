package top.anly.business.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.anly.common.param.IdParam;
import top.anly.common.util.Result;
import java.util.List;
import top.anly.business.role.domain.Role;
import top.anly.business.role.controller.param.RoleAddParam;
import top.anly.business.role.controller.param.RoleModifyParam;
import top.anly.business.role.controller.param.RoleListParam;
import top.anly.business.role.controller.param.RoleListPageParam;
import top.anly.business.role.controller.res.RoleRes;


/**
 * 角色基本信息SERVICE层接口
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
public interface RoleService extends IService<Role> {

	/**
	 * 批量删除
	 * @param params
	 */
	void batchRemoveByIds(List<IdParam> params);


}
