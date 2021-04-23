package top.anly.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * id 参数
 *
 * @author yinhaijing
 * @date 2019/11/28 13:46
 */
@ApiModel("ID查询参数")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdParam {

    @ApiModelProperty(value = "业务ID", example = "1")
    private Integer id;
}
