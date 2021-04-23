package top.anly.common.util.generator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * orm
 *
 * @authorComponentScan ：yinhaijign
 * @date ：2019/5/14 08:55
 */
@Slf4j
@Component
public class OrmBiz {

    @Autowired
    private OrmFactory ormFactory;
    @Autowired
    private DataSource dataSource;

    public List<String> getTables() {
        List<String> tablelist = new ArrayList<String>();
        Connection conn = null;
        OrmDbType ormdb = ormFactory.createOrmDB(dataSource);
        if (ormdb == null) {
            return new ArrayList<>();
        }
        DataSource ds = ormdb.getDataSource();
        try {
            conn = ds.getConnection();
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet tableSet = null;
            if (ormdb.isOracle()) {
                String schem = ormdb.getSchem();
                tableSet = databaseMetaData.getTables(null, schem, "%",
                        new String[]{"TABLE"});
            } else {
                tableSet = databaseMetaData.getTables(null, "%", "%",
                        new String[]{"TABLE"});
            }
            while (tableSet.next()) {
                String tableName = tableSet.getString("TABLE_NAME");
                if (ormdb.isOracle()) {
                    if (tableName.indexOf("==$0") != -1) {
                        continue;
                    }
                }
                tablelist.add(tableName);
            }
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tablelist;
    }

    public int convert(List<ConvertInfo> infolist) {
        //遍历表
        for (ConvertInfo info : infolist) {
            //表转换
            info.convert();
            List<ConvertColumn> collist = getColumnInfo(info.getTableName());
            info.setColist(collist);
            log.debug("包名：" + info.getPackName() + ",表名：" + info.getTableName()
                    + ",类名：" + info.getClassName());
        }
        saveOrmFile(infolist);
        return 0;
    }

    private void saveOrmFile(List<ConvertInfo> infolist) {
        //遍历表
        for (ConvertInfo info : infolist) {
            AutoCreateClass acc = new AutoCreateClass(info);
            acc.saveAddParamClass();//add入参
            acc.saveModifyParamClass();//modify入参
            acc.saveListParamClass();//list入参
            acc.saveListPageParamClass();//listPage入参
            acc.saveResClass();//出参
            acc.saveEntityClass();//domain
            acc.saveDaoClass();//dao
            acc.saveIBizClass();//service
            acc.saveBizClass();//serviceImpl
            acc.saveActionClass();//controller
        }
    }

    private List<ConvertColumn> getColumnInfo(String tableName) {
        List<ConvertColumn> colset = new ArrayList<ConvertColumn>();
        DatabaseMetaData databaseMetaData;
        Connection conn = null;
        OrmDbType ormdb = ormFactory.createOrmDB(dataSource);
        if (ormdb == null) {
            return new ArrayList<>();
        }
        DataSource ds = ormdb.getDataSource();
        try {
            conn = ds.getConnection();
            databaseMetaData = conn.getMetaData();
            ResultSet columnSet = null;
            if (ormdb.isOracle()) {
                columnSet = databaseMetaData.getColumns(null, ormdb.getSchem(),
                        tableName, "%");
            } else {
                columnSet = databaseMetaData.getColumns(null, "%", tableName, "%");
            }
            //获取所有列
            while (columnSet.next()) {
                ConvertColumn col = new ConvertColumn();
                String colName = columnSet.getString("COLUMN_NAME");
                short ordinalPosition = columnSet.getShort("ORDINAL_POSITION");//在索引列顺序号;TYPE为 tableIndexStatistic 时该序列号为零;
//                System.out.println( ordinalPosition + " - " + colName );
                col.setColName(colName);
                col.setComment(columnSet.getString("REMARKS"));
                String typeName = columnSet.getString("TYPE_NAME").toLowerCase();
                col.setColType(typeName);
                col.setNullable("YES".equals(columnSet.getString("IS_NULLABLE")));
                if (ormdb.isOracle()) {
                    col.setAutoIncrement(false);
                    //判断是否主键
                } else if (ormdb.isMssql()) {
                    if (typeName.endsWith(" identity")) {
                        //自增
                        col.setAutoIncrement(true);
                    }
                } else {
                    col.setAutoIncrement("YES".equals(columnSet.getString("IS_AUTOINCREMENT")));
                }
                //表列--》 字段的转换
                col.convert();
                colset.add(col);
            }
            ResultSet pkCols = null;
            if (ormdb.isOracle()) {
                pkCols = databaseMetaData.getPrimaryKeys(null, ormdb.getSchem(),
                        tableName);
            } else if (ormdb.isMssql()) {
                pkCols = databaseMetaData.getPrimaryKeys(null, "dbo", tableName);
            } else if (ormdb.isPostgreSQL()) {
                pkCols = databaseMetaData.getPrimaryKeys(null, null, tableName);
            } else {
                pkCols = databaseMetaData.getPrimaryKeys(null, "%", tableName);
            }
            if (pkCols == null) {
                return colset;
            }
            while (pkCols.next()) {
                String colName = pkCols.getString("COLUMN_NAME");
                log.debug(colName + "主键");
                for (ConvertColumn col : colset) {
                    if (colName.equals(col.getColName())) {
                        //设置为主键
                        col.setID(true);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return colset;
    }




    public static String captureName(String name) {
        // 首字母大写,且当第二个字母为小写时
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        char[] cs = name.toCharArray();
        int strlen = cs.length;
        if (strlen == 0) {
            return name;
        } else if (1 == strlen) {
            if (Character.isLowerCase(cs[0])) {
                cs[0] -= 32;
            }
        } else {
            if (Character.isLowerCase(cs[0]) && Character.isLowerCase(cs[1])) {
                cs[0] -= 32;
            }
        }
        return String.valueOf(cs);
    }

    /**
     * 首字母小写
     * @param name
     * @return
     */
    public static String firstToLowerCase(String name) {
        char[] cs = name.toCharArray();
        if (Character.isUpperCase(cs[0])) {
            cs[0] += 32;
        }
        return String.valueOf(cs);
    }

    /**
     * 名字转换
     * @param srcName
     * @param flag
     * @return
     */
    public static String changeName(String srcName, boolean flag) {
        if (StringUtils.isEmpty(srcName)) {
            return srcName;
        }
        StringBuilder strBuild = new StringBuilder();
        String splitTag="_";
        for (String name : srcName.toLowerCase().split(splitTag)) {
            // 首单词中的首字母，不需要转换大写 如 类属性
            if (flag) {
                flag = false;
                strBuild.append(name);
                continue;
            }
            strBuild.append(captureName(name));
        }
        return strBuild.toString();
    }
}
