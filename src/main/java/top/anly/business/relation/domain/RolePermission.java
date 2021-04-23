package top.anly.business.relation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import top.anly.common.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;


/**
 * 角色-权限实体类
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@TableName("role_permission")
@Data
public class RolePermission extends BaseDomain {
	/**
	 * 自增主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 角色id
	 */
	private Integer rid;

	/**
	 * 权限id
	 */
	private Integer pid;



}
