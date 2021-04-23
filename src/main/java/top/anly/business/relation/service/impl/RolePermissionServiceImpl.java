package top.anly.business.relation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import top.anly.business.relation.domain.RolePermission;
import top.anly.business.relation.dao.RolePermissionDao;
import top.anly.business.relation.service.RolePermissionService;
import top.anly.business.relation.controller.param.RolePermissionAddParam;
import top.anly.business.relation.controller.param.RolePermissionModifyParam;
import top.anly.business.relation.controller.param.RolePermissionListParam;
import top.anly.business.relation.controller.param.RolePermissionListPageParam;
import top.anly.business.relation.controller.res.RolePermissionRes;
import top.anly.common.param.IdParam;
import top.anly.exception.AppError;
import top.anly.exception.AppException;


/**
 * 角色-权限SERVICE层接口实现类
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao,RolePermission> implements RolePermissionService {

	@Override
	public void batchRemoveByIds(List<IdParam> params) {
		List<Integer> ids = params.stream().map(IdParam::getId).collect(Collectors.toList());
		if(!removeByIds(ids)){
			throw new AppException(AppError.FAILED);
		}
	}


}
