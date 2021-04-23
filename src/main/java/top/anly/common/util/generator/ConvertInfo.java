package top.anly.common.util.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

/**
 * 表转换类信息
 *
 * @author ：yinhaijign
 * @date ：2019/5/14 08:55
 */
public class ConvertInfo {

    @ApiModelProperty(value = "包名", example = "equipment", required = true)
    private String packName;

    private String className;

    @ApiModelProperty(value = "表名", example = "hy_equipment_ledger", required = true)
    private String tableName;

    private String comment;

    @ApiModelProperty(value = "保存路径", example = "D:\\CodeSpace")
    private String savePath;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者", example = "孙晓东", required = true)
    private String author;
    /**
     * 表注释
     */
    @ApiModelProperty(value = "表注释", example = "设备台账")
    private String content;
    /**
     * 当前日期 yyyy-MM-dd
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate creatDate;

    /**
     * 表转换类
     *
     * @author ：yinhaijign
     * @date ：2019/5/14 08:55
     */
    public void convert() {
        //字段名
        className = OrmBiz.changeName(tableName, false);
    }

    public String getIdName() {
        for (ConvertColumn col : colist) {
            if (col.isID()) {
                return col.getFieldName();
            }
        }
        return null;
    }

    private List<ConvertColumn> colist;

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public List<ConvertColumn> getColist() {
        return colist;
    }

    public void setColist(List<ConvertColumn> colist) {
        this.colist = colist;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatDate() {
        creatDate = LocalDate.now();
        return creatDate;
    }

    public void setCreatDate(LocalDate creatDate) {
        this.creatDate = creatDate;
    }
}
