package top.anly.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 根据表名称自动生成相关代码
 *
 * @author zhenhua.huo
 * @date 2019/12/18 下午2:01
 */
@ApiModel("代码自动生成接口入参")
@Data
public class ConvertTableParam {
    /**
     * 包名
     */
    @ApiModelProperty(value = "包名", example = "station", required = true)
    private String packName;

    /**
     * 表名
     */
    @ApiModelProperty(value = "表名", example = "station", required = true)
    private String tableName;

    /**
     * 表名
     */
    @ApiModelProperty(value = "表注释", example = "工序", required = true)
    private String tableNameDesc;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者", example = "zhenhuo.huo", required = true)
    private String author;
    /**
     * 保存路径
     */
    @ApiModelProperty(value = "保存路径", example = "D:\\CodeSpace")
    private String savePath;

}
