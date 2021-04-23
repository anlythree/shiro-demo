package top.anly.business.role.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import top.anly.common.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;


/**
 * 角色基本信息实体类
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@TableName("role")
@Data
public class Role extends BaseDomain {
	/**
	 * 自增主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 角色名
	 */
	private String name;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 状态
	 */
	private Integer status;



}
