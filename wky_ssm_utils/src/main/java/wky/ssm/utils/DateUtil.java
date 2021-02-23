package wky.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /*日期转为字符串*/
    public static String date2String(Date date,String patt){
        SimpleDateFormat sf = new SimpleDateFormat(patt);
        String format = sf.format(date);
        return format;
    }
    /*字符串转为日期*/
    public static Date string2Date(String str,String patt) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(patt);
        Date date = sf.parse(str);
        return date;
    }
}
