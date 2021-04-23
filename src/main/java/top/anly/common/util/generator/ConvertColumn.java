package top.anly.common.util.generator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * 列转换实体
 *
 * @author ：yinhaijign
 * @date ：2019/5/14 08:55
 */
@SuppressWarnings(value = "all")
public class ConvertColumn {

    private static Map<String, String> typeMap = new HashMap<String, String>();
    private static Map<String, String> typeMapXml = new HashMap<String, String>();

    protected Log log = LogFactory.getLog(ConvertColumn.class);


    static {

        typeMap.put("bit", "Boolean");

        typeMap.put("uniqueidentifier", "String");

        typeMap.put("varchar", "String");
        typeMap.put("nvarchar", "String");
        typeMap.put("char", "String");
        typeMap.put("clob", "String");

        typeMap.put("tinytext", "String");
        typeMap.put("text", "String");
        typeMap.put("mediumtext", "String");
        typeMap.put("longtext", "String");

        typeMap.put("tinyint", "Integer");
        typeMap.put("smallint", "Integer");
        typeMap.put("mediumint", "Integer");
        typeMap.put("number", "Integer");
        typeMap.put("int", "Integer");
        typeMap.put("bigint", "Long");

        typeMap.put("float", "Float");
        typeMap.put("double", "Double");

        typeMap.put("decimal", "java.math.BigDecimal");

        typeMap.put("datetime", "java.util.Date");
        typeMap.put("date", "java.time.LocalDate");
        typeMap.put("time", "java.util.Date");
        typeMap.put("timestamp", "java.time.LocalDateTime");
        typeMap.put("year", "java.util.Date");

        typeMap.put("blob", "byte[]");
        typeMap.put("longblob", "byte[]");
        //postgres

        typeMap.put("numeric", "java.math.BigDecimal");
        typeMap.put("int2", "Integer");
        typeMap.put("int4", "Integer");
        typeMap.put("text", "String");
        typeMap.put("serial", "Integer");


        //mybatis 类型
        typeMapXml.put("varchar", "VARCHAR");
        typeMapXml.put("nvarchar", "NVARCHAR");
        typeMapXml.put("int", "INTEGER");
        typeMapXml.put("date", "TIMESTAMP");
        typeMapXml.put("datetime", "TIMESTAMP");
        typeMapXml.put("float", "FLOAT");
        typeMapXml.put("timestamp", "TIMESTAMP");
        typeMapXml.put("int2", "INTEGER");
        typeMapXml.put("numeric", "FLOAT");
        typeMapXml.put("int4", "INTEGER");
        typeMapXml.put("text", "NVARCHAR");
        typeMapXml.put("serial", "INTEGER");

    }

    /**
     * 表列属性转换为 类域字段
     *
     * @param
     * @return
     */
    public void convert() {
        //字段名   ----------- 表中域名 转换成 属性名称
        fieldName = OrmBiz.changeName(colName, true);
        //字段类型
        String type = colType;
        for (String key : typeMap.keySet()) {
            if (type.startsWith(key)) {
                fieldType = typeMap.get(key);
                break;
            }
        }
        //默认类型
        if (fieldType == null) {
            fieldType = "Object";
            log.debug(colType + "映射匹配空");
        }

        //获取mabatis 类型
        for (String key : typeMapXml.keySet()) {
            if (type.startsWith(key)) {
                myxml = typeMapXml.get(key);
                break;
            }
        }
        //默认类型
        if (myxml == null) {
            myxml = "Object";
            log.debug(colType + "映射匹配空");
        }

    }

    /**
     * 列名
     */
    private String colName;

    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String fieldType;
    /**
     * 列类型
     */
    private String colType;
    /**
     * 是否为主键
     */
    private boolean isID;
    /**
     * 是否可为null
     */
    private boolean nullable;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 注解
     */
    private String comment;
    /**
     * 自增
     */
    private boolean autoIncrement;
    /**
     * mybatis 类型
     */
    private String myxml;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public boolean isID() {
        return isID;
    }

    public void setID(boolean isID) {
        this.isID = isID;
    }


    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public String toString() {
        return "ConvertColumn [colName=" + colName + ", fieldName=" + fieldName
                + ", fieldType=" + fieldType + ", colType=" + colType
                + ", isID=" + isID + ", nullable=" + nullable + ", defaultValue="
                + defaultValue + ", comment=" + comment + ", autoIncrement="
                + autoIncrement + "]";
    }

    public static String test() {
        ConvertColumn col = new ConvertColumn();
        col.setColName("menu_id");
        col.setColType("int(11)");
        col.convert();
        return col.toString();
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getMyxml() {
        return myxml;
    }

    public void setMyxml(String myxml) {
        this.myxml = myxml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConvertColumn that = (ConvertColumn) o;
        return isID == that.isID &&
                nullable == that.nullable &&
                autoIncrement == that.autoIncrement &&
                Objects.equals(log, that.log) &&
                Objects.equals(colName, that.colName) &&
                Objects.equals(fieldName, that.fieldName) &&
                Objects.equals(fieldType, that.fieldType) &&
                Objects.equals(colType, that.colType) &&
                Objects.equals(defaultValue, that.defaultValue) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(myxml, that.myxml);
    }

    @Override
    public int hashCode() {
        return Objects.hash(log, colName, fieldName, fieldType, colType, isID, nullable, defaultValue, comment, autoIncrement, myxml);
    }
}
