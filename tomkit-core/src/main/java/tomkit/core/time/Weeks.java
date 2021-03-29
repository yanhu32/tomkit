package tomkit.core.time;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 星期工具类
 *
 * @author yh
 * @since 2021/3/23
 */
public class Weeks {

    /**
     * 获取星期的中文表示
     *
     * @param week 星期
     * @return 星期的中文表示
     */
    public static String weekChs(DayOfWeek week) {
        if (null != week) {
            switch (week) {
                case MONDAY:
                    return "星期一";
                case TUESDAY:
                    return "星期二";
                case WEDNESDAY:
                    return "星期三";
                case THURSDAY:
                    return "星期四";
                case FRIDAY:
                    return "星期五";
                case SATURDAY:
                    return "星期六";
                case SUNDAY:
                    return "星期日";
                default:
            }
        }
        return "";
    }

    /**
     * 获取最新一次星期的日期
     *
     * @param week 星期
     * @return 最新一次星期的日期
     */
    public static LocalDate nextWeekDate(DayOfWeek week) {
        if (null == week) {
            return null;
        }
        LocalDate today = LocalDate.now();
        DayOfWeek todayWeek = today.getDayOfWeek();

        int compare = todayWeek.compareTo(week);
        if (compare > 0) {
            return today.plusDays(7 - (todayWeek.getValue() - week.getValue()));
        } else if (compare == 0) {
            return today;
        } else {
            // compare < 0;
            return today.plusDays((week.getValue() - todayWeek.getValue()));
        }
    }

    /**
     * 获取最新一次星期的日期字符串形式
     *
     * @param week 星期
     * @return 最新一次星期的日期字符串
     */
    public static String nextWeekDateStr(DayOfWeek week) {
        if (null == week) {
            return null;
        }
        return DateTimes.format(nextWeekDate(week));
    }

}
