package top.anly.common.util.generator;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractOrm类的实例工厂
 *
 * @author ：yinhaijign
 * @date ：2019/5/14 08:55
 */
@Component
@SuppressWarnings(value = "all")
public class OrmFactory {
    private Map<String, OrmDbType> dbtypeMap = new HashMap<>();

    public OrmDbType createOrmDB(DataSource dataSource) {
        String code = dataSource.hashCode() + "";
        OrmDbType ormdb = dbtypeMap.get(code);
        if (ormdb == null) {
            ormdb = new OrmDbType(dataSource);
            dbtypeMap.put(code, ormdb);
        }
        return ormdb;
    }
}
