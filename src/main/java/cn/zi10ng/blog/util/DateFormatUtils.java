package cn.zi10ng.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zi10ng
 * @date 2019年7月24日20:54:54
 * 日期转换工具类
 */
public class DateFormatUtils {
    /**
     * 日期转换为字符串
     * @param date 日期
     * @param str 字符串
     * @return 字符串
     */
    public static String data2String(Date date, String str){
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        return sdf.format(date);
    }
}
