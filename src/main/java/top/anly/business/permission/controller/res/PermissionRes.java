package top.anly.business.permission.controller.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 权限基本信息返回属性
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@ApiModel("权限基本信息返回属性")
@Data
public class PermissionRes {
	/**
	 * 自增主键
	 */
	@ApiModelProperty(value = "自增主键", example = "")
	private Integer id;

	/**
	 * 上级id
	 */
	@ApiModelProperty(value = "上级id", example = "")
	private Integer pid;

	/**
	 * 权限名
	 */
	@ApiModelProperty(value = "权限名", example = "")
	private String name;

	/**
	 * 类型（0：菜单，1：功能）
	 */
	@ApiModelProperty(value = "类型（0：菜单，1：功能）", example = "")
	private Integer type;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", example = "")
	private Integer sort;

	/**
	 * 地址
	 */
	@ApiModelProperty(value = "地址", example = "")
	private String url;

	/**
	 * 权限编码
	 */
	@ApiModelProperty(value = "权限编码", example = "")
	private String permCode;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标", example = "")
	private String icon;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", example = "")
	private String description;

	/**
	 * 状态（0：禁用，1：正常）
	 */
	@ApiModelProperty(value = "状态（0：禁用，1：正常）", example = "")
	private Integer status;



}
