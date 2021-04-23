package top.anly.common.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 同步pdm数据入参
 * @author wangli
 * @date 2020/8/11 19:26
 */
@Data
public class CopyMaterialDataToGoodsParams {

    @ApiModelProperty("每轮同步的数据条数")
    private Integer totalByOnce;

    @ApiModelProperty("是否同步已删除的数据")
    private String isRemoved;
}
