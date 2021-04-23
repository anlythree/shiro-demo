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
import top.anly.business.relation.domain.UserRole;
import top.anly.business.relation.dao.UserRoleDao;
import top.anly.business.relation.service.UserRoleService;
import top.anly.business.relation.controller.param.UserRoleAddParam;
import top.anly.business.relation.controller.param.UserRoleModifyParam;
import top.anly.business.relation.controller.param.UserRoleListParam;
import top.anly.business.relation.controller.param.UserRoleListPageParam;
import top.anly.business.relation.controller.res.UserRoleRes;
import top.anly.common.param.IdParam;
import top.anly.exception.AppError;
import top.anly.exception.AppException;


/**
 * 用户_角色SERVICE层接口实现类
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao,UserRole> implements UserRoleService {

	@Override
	public void batchRemoveByIds(List<IdParam> params) {
		List<Integer> ids = params.stream().map(IdParam::getId).collect(Collectors.toList());
		if(!removeByIds(ids)){
			throw new AppException(AppError.FAILED);
		}
	}


}
