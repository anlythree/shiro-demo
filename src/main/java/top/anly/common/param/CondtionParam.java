package top.anly.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * id 参数
 *
 * @author yinhaijing
 * @date 2019/11/28 13:46
 */
@ApiModel("多条件模糊查询")
@Data
public class CondtionParam {
    @ApiModelProperty(value = "查询条件", example = "seach")
    private String condtion;
}
