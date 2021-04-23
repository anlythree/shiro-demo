package top.anly.business.permission.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import top.anly.common.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;


/**
 * 权限基本信息实体类
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@TableName("permission")
@Data
public class Permission extends BaseDomain {
	/**
	 * 自增主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 上级id
	 */
	private Integer pid;

	/**
	 * 权限名
	 */
	private String name;

	/**
	 * 类型（0：菜单，1：功能）
	 */
	private Integer type;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 地址
	 */
	private String url;

	/**
	 * 权限编码
	 */
	private String permCode;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 状态（0：禁用，1：正常）
	 */
	private Integer status;



}
