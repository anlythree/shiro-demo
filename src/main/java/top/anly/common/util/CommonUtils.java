package top.anly.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共工具类
 *
 * @author ：anlythree
 * @date ：2019/5/28 08:55
 */
@Slf4j
@SuppressWarnings(value = "all")
public final class CommonUtils {
    /**
     * 文件写入
     *
     * @param body byte[]
     * @return boolean
     */
    public static boolean writeFile(String path, byte[] body) {
        File file = new File(path);
        File pfile = file.getParentFile();
        if (pfile.exists() == false) {
            pfile.mkdirs();
        }
        boolean ret = true;
        try {
            file.createNewFile();
            OutputStream out = new FileOutputStream(file);
            out.write(body);
            out.close();
        } catch (IOException e) {
            ret = false;
        }
        return ret;
    }

    /**
     * 正则表达式匹配字符串 !
     *
     * @param content 源字符串
     * @param regx    正则表达式
     * @return 匹配的内容
     */
    public static String regx(String content, String regx) {
        if (content == null || regx == null) {
            return null;
        }
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(content);
        if (m.find()) {
            if (m.groupCount() == 1) {
                return m.group(1);
            }
        }
        return null;
    }

    /**
     * 文件写入
     *
     * @param body String
     * @return boolean
     */
    public static boolean writeFile(String path, String body) {
        try {
            return writeFile(path, body.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * md5 加密
     *
     * @param username
     * @param pwd
     * @return
     */
    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + "ever"), 2).toHex();
        return md5Pwd;
    }

    /**
     * 获取非空字符串
     *
     * @param targetString 判断目标
     * @return
     */
    public static String notNullString(String targetString) {
        return null == targetString ? "" : targetString;
    }

    /**
     * 获取非空字符串
     *
     * @param targetString  判断目标
     * @param replaceString 若为null替换成该字符串
     * @return
     */
    public static String notNullString(String targetString, String replaceString) {
        return StringUtils.isEmpty(targetString) ? replaceString : targetString;
    }

    /**
     * 获取非空BigDecimal
     *
     * @param targetString  判断目标
     * @param replaceString 若为null替换成该字符串
     * @return
     */
    public static BigDecimal notNullBigDecimal(BigDecimal bigDecimal, BigDecimal replaceBigDecimal) {
        return null == bigDecimal ? replaceBigDecimal : bigDecimal;
    }

    /**
     * 获取非空BigDecimal
     *
     * @param targetString  判断目标
     * @param replaceString 若为null替换成该字符串
     * @return
     */
    public static BigDecimal notNullBigDecimal(BigDecimal bigDecimal) {
        return null == bigDecimal ? new BigDecimal(0.00) : bigDecimal;
    }

    /**
     * 判断是否是数字
     *
     * @param target
     * @return 数字:true 非数字：false
     */
    public static boolean isNumber(String target) {
        return NumberUtils.isCreatable(target);
    }

    public static List<Integer> StrListToIntList(List<String> strList) {
        ArrayList<Integer> intList = new ArrayList();
        for (String s : strList) {
            intList.add(Integer.parseInt(s));
        }
        return intList;
    }

    public static List<String> StrListToStrList(List<String> strList) {
        ArrayList<String> strResList = new ArrayList();
        for (String s : strList) {
            strResList.add(s);
        }
        return strResList;
    }

    /**
     * 返回非null的list
     * @param list
     * @return
     */
    public static List nullListToEmptyList(List list) {
        return CollectionUtils.isEmpty(list) ? new ArrayList(0) : list;
    }

    /**
     * list转成set
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Set<T> ListToSet(List<T> list){
        if(CollectionUtils.isEmpty(list)){
            return new HashSet<T>(0);
        }
        return new HashSet<T>(list);
    }

}
