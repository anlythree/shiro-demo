package top.anly.business.role.controller.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 角色基本信息返回属性
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@ApiModel("角色基本信息返回属性")
@Data
public class RoleRes {
	/**
	 * 自增主键
	 */
	@ApiModelProperty(value = "自增主键", example = "")
	private Integer id;

	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名", example = "")
	private String name;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", example = "")
	private Integer sort;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", example = "")
	private String description;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态", example = "")
	private Integer status;



}
