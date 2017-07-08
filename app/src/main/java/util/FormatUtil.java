package util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 刘柳青 on 2017/1/26.
 */

public class FormatUtil {

    public static String formatDate(String dateString) {

          Date currentDate = new Date();
          SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

          try {
              Date date = format.parse(dateString);
              if (format.format(currentDate).equals(dateString))
                  return "今日新闻";
              else {
                  format = new SimpleDateFormat("yyyy-MM-dd");

                  return format.format(date);
              }

          }catch(Exception e) {
            e.printStackTrace();
          }
        return "";
    }
}
