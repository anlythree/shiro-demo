package top.anly.business.relation.controller.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;


/**
 * 用户_角色修改入参
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@ApiModel("用户_角色修改入参")
@Data
public class UserRoleModifyParam {
	/**
	 * 自增主键
	 */
	@ApiModelProperty(value = "自增主键", example = "")
	private Integer id;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id", example = "")
	private Integer uid;

	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id", example = "")
	private Integer rid;



}
