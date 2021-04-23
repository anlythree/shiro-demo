package top.anly.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * domian 父类
 *
 * @author yinhaijing
 * @date 2019-05-13
 */
@Data
public class BaseDomain {
    /**
     * 当前页数
     */
    @TableField(exist = false)
    private int pageNumber;
    /**
     * 分页数量
     */
    @TableField(exist = false)
    private int pageSize;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(select = true, fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(select = true, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
    /**
     * 公司
     */
    @TableField(select = true, fill = FieldFill.INSERT)
    private Integer companyId;
}
