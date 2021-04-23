package top.anly.business.relation.controller.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;


/**
 * 角色-权限分页查询入参
 *
 * @author ：anlythree
 * @date ：2021-04-23
 */
@ApiModel("角色-权限分页查询入参")
@Data
public class RolePermissionListPageParam extends RolePermissionListParam{
	/**
	 * 当前页码
	 */
	@ApiModelProperty(value = "当前页码", example = "1",required = true)
	@NotNull(message = "当前页码为空")
	private Integer pageNumber;
	/**
	 * 每页数量
	 */
	@ApiModelProperty(value = "每页数量", example = "10",required = true)
	@NotNull(message = "每页数量为空")
	private Integer pageSize;


}
