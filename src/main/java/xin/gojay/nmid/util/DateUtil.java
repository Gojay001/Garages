package xin.gojay.nmid.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Gojay
 * @date 2018/5/23
 */
public class DateUtil {
    public static Date stringToDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate =  simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("字符串转化日期出错！");
            e.printStackTrace();
        }
        if (utilDate == null) {
            return null;
        }
        return new Date(utilDate.getTime());
    }

    /*
    public static void main(String[] args) {
        String text = "2018-05-23";
        System.out.println(stringToDate(text));
    }
    */
}
