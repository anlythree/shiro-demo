package top.anly.business.role.service.impl;

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
import top.anly.business.role.domain.Role;
import top.anly.business.role.dao.RoleDao;
import top.anly.business.role.service.RoleService;
import top.anly.business.role.controller.param.RoleAddParam;
import top.anly.business.role.controller.param.RoleModifyParam;
import top.anly.business.role.controller.param.RoleListParam;
import top.anly.business.role.controller.param.RoleListPageParam;
import top.anly.business.role.controller.res.RoleRes;
import top.anly.common.param.IdParam;
import top.anly.exception.AppError;
import top.anly.exception.AppException;


/**
 * 角色基本信息SERVICE层接口实现类
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao,Role> implements RoleService {

	@Override
	public void batchRemoveByIds(List<IdParam> params) {
		List<Integer> ids = params.stream().map(IdParam::getId).collect(Collectors.toList());
		if(!removeByIds(ids)){
			throw new AppException(AppError.FAILED);
		}
	}


}
