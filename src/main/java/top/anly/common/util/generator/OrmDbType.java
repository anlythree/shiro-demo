package top.anly.common.util.generator;


import lombok.extern.slf4j.Slf4j;
import top.anly.common.util.CommonUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * 数据库信息
 *
 * @author ：yinhaijign
 * @date ：2019/5/14 08:55
 */
@Slf4j
@SuppressWarnings(value = "all")
public class OrmDbType {

    private static String mysql = "MySQL";
    private static String h2 = "H2";
    private static String oracle = "Oracle";
    private static String mssqlserver = "Microsoft SQL Server";
    private static String postgreSQL = "PostgreSQL";
    private String dbName;
    private String dbVersion;
    private DataSource dataSource;
    private DBType dbType;
    private String schem = "%";

    public boolean isOracle() {
        return DBType.oracle == dbType;
    }

    public boolean isMysql() {
        return DBType.mysql == dbType;
    }

    public boolean isMssql() {
        return DBType.sqlserver == dbType;
    }

    public boolean isPostgreSQL() {
        return DBType.postgreSQL == dbType;
    }

    public OrmDbType(DataSource dataSource) {
        Connection conn = null;
        if (dbType != null) {
            return;
        }
        this.dataSource = dataSource;
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            dbName = meta.getDatabaseProductName();
            dbVersion = meta.getDatabaseProductVersion();
            if (mssqlserver.equals(dbName)) {
                dbType = DBType.sqlserver;
            } else if (mysql.equals(dbName) || h2.equals(dbName)) {
                //mysql h2语法基本一致
                dbType = DBType.mysql;
            } else if (oracle.equals(dbName)) {
                dbType = DBType.oracle;
                dbVersion = CommonUtils.regx(dbVersion, "\\s(\\d.[^\\s]+\\d)\\s");
                //postgreSQL h2语法基本一致
            } else if (postgreSQL.equals(dbName)) {
                dbType = DBType.postgreSQL;
            } else {
                log.error("暂时不支持" + dbName + "数据库");
            }
            log.debug("数据库:" + dbName + ",版本:" + dbVersion + ",识别:" + dbType);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public String getSchem() {
        return schem;
    }

    enum DBType {
        //oracle
        oracle,
        //mysql
        mysql,
        //sqlserver
        sqlserver,
        //postgreSQL
        postgreSQL

    }

}
