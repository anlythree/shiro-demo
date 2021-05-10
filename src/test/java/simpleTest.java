import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * @author wangli
 * @date 2021/4/26 11:48
 */
public class simpleTest {

    @Test
    public void test2() {
        String password = "132132";//明码
        String algorithmName = "MD5";//加密算法
        Object source = password;//要加密的密码

        //盐值，一般都是用户名或者userid，要保证唯一
        Object salt = ByteSource.Util.bytes("sale-test");
        //加密次数
        int hashIterations = 1;

        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        //打印出经过盐值、加密次数、md5后的密码
        System.out.println(simpleHash);
    }

    /**
     * 使用shiro自带的加密工具
     */
    @Test
    public void test3() {
        //需要传入两个参数，第一个是密码，第二个是盐
        System.out.println(new Md5Hash("132132", "sale-test").toString()) ;
    }
}
