import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Pcm {
    public static void main(String[] args) {

        String date ="28/02/2003 00:00:01";
        String nowText = "28/02/2003 00:00:02";
        int currentYear = LocalDate.now().getYear();

        Date now = DateUtil.parseDate(nowText,
            DateUtil.DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM_SS);
        Date ndate = DateUtil.parseDate(date,
            DateUtil.DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM_SS);
       if (!now.after(ndate)){
           System.out.println("ok");
       }


//        Date contractAnniversary = contractAnniversary(date, now);
//        System.out.println(contractAnniversary);
//
//
//        if (!isWithinTheTimeLimit(contractAnniversary, now)) {
//            System.out.println("Từ ngày "+ DateUtil.dateToStr(DateUtil.addDays(contractAnniversary, -30), DateUtil.DATE_FORMAT_DD_MM_YYYY));
//            System.out.println("đến  ngày "+ DateUtil.dateToStr(DateUtil.addDays(contractAnniversary, 60), DateUtil.DATE_FORMAT_DD_MM_YYYY));
//        }




    }

    public static boolean isWithinTheTimeLimit(Date contractAnniversary, Date now) {
        return DateUtil.addDays(contractAnniversary, -30).before(now) &&
            DateUtil.addDays(contractAnniversary, 61).after(now);
    }

    public static Date contractAnniversary(String policyStartDateText, Date now) {
        Date beforeNowNearest = null;
        Date afterNowNearest = null;
        if (DateUtil.dateCheck(policyStartDateText, DateUtil.DATE_FORMAT_DD_MM_YYYY)) {

            String policyAnniversaryText = policyStartDateText.substring(0, 5);
            int currentYear = now.getYear() + 1900;
            Date policyAnniversaryThisYear = policyAnniversaryByYear(policyAnniversaryText, currentYear);

            if (!isLeapYear(currentYear) && policyAnniversaryText.equals(LeapYearConstant.DATE_29_02)) {
                policyAnniversaryThisYear = policyAnniversaryByYear(LeapYearConstant.DATE_28_02, currentYear);
            }

            if (policyAnniversaryThisYear.before(now)) {
                beforeNowNearest = policyAnniversaryThisYear;
                afterNowNearest = policyAnniversaryByYear(policyAnniversaryText, currentYear + 1);

                if (isLeapYear(currentYear + 1) && policyAnniversaryText.equals(LeapYearConstant.DATE_29_02)) {
                    afterNowNearest = policyAnniversaryByYear(LeapYearConstant.DATE_29_02, currentYear + 1);
                }

                if (!isLeapYear(currentYear + 1) && policyAnniversaryText.equals(LeapYearConstant.DATE_29_02)) {
                    afterNowNearest = policyAnniversaryByYear(LeapYearConstant.DATE_28_02, currentYear + 1);
                }
            }

            if (policyAnniversaryThisYear.after(now)) {
                afterNowNearest = policyAnniversaryThisYear;
                beforeNowNearest = policyAnniversaryByYear(policyAnniversaryText, currentYear - 1);

                if (isLeapYear(currentYear - 1) && policyAnniversaryText.equals(LeapYearConstant.DATE_29_02)) {
                    beforeNowNearest = policyAnniversaryByYear(LeapYearConstant.DATE_29_02, currentYear - 1);
                }

                if (!isLeapYear(currentYear - 1) && policyAnniversaryText.equals(LeapYearConstant.DATE_29_02)) {
                    beforeNowNearest = policyAnniversaryByYear(LeapYearConstant.DATE_28_02, currentYear - 1);
                }
            }
        }

        return contractAnniversary(beforeNowNearest, afterNowNearest, now);
    }

    public static Date contractAnniversary(Date policyAnniversaryBeforeNowNearest,
                                    Date policyAnniversaryAfterNowNearest, Date now) {
        if (policyAnniversaryBeforeNowNearest.before(now)
            && DateUtil.addDays(policyAnniversaryBeforeNowNearest, 61).after(now)) {
            System.out.println(DateUtil.addDays(policyAnniversaryBeforeNowNearest, 60));
            return policyAnniversaryBeforeNowNearest;
        }
        System.out.println(DateUtil.addDays(policyAnniversaryBeforeNowNearest, 60));
        return policyAnniversaryAfterNowNearest;
    }

    public static Date policyAnniversaryByYear(String policyAnniversary, int year) {
        return DateUtil.parseDate(policyAnniversary + "/" + year,
            DateUtil.DATE_FORMAT_DD_MM_YYYY);
    }

    public static boolean isLeapYear(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) ||
            (year % 400 == 0));
    }


    public static class DateUtil {

        private DateUtil() {
        }



        public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

        public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

        public static final String DATE_TIME_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyy HH:mm:ss";

        public static final String SHORT_DATE_TIME_FORMAT = "yyyyMMdd"; // format for template service

        public static final String FUND_DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

        public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

        public static final String DATE_FORMAT_SIMPLE = "ddMMyyyy";

        public static final String DATE_FORMAT_YYYY_MM_DD_SIMPLE = "yyyyMMdd";

        public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";

        public static final String TIME_FORMAT_SIMPLE = "HHmmss";

        public static String format(Date value) {
            return format(value, DEFAULT_DATE_FORMAT);
        }

        public static String format(Date value, String format) {
            if (value == null) {
                return "";
            }

            if (format == null || format.length() == 0) {
                format = DEFAULT_DATE_FORMAT;
            }
            DateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
            String result = df.format(value);
            if (result.length() != format.length()) {

            }
            return result;
        }


        public static Date format(String value) {
            return format(value, DEFAULT_DATE_FORMAT);
        }


        public static Date format(String value, String format) {
            if (value == null || value.length() == 0) {
                return null;
            }
            if (format == null || format.length() == 0) {
                format = DEFAULT_DATE_FORMAT;
            }

            if (format.length() > value.length()) {
                format = format.substring(0, value.length());
            }

            Date result = null;
            SimpleDateFormat df = null;
            df = new SimpleDateFormat(format);
            try {
                result = df.parse(value);
            } catch (ParseException pe) {

            }
            if (result != null) {
                return result;
            }
            return null;
        }

        public static String format(Timestamp value, String format) {
            if (value == null) {
                return "";
            }

            if (format == null || format.length() == 0) {
                format = DEFAULT_DATE_FORMAT;
            }
            DateFormat df = new SimpleDateFormat(format);
            String result = df.format(value);
            if (result.length() != format.length()) {

            }
            return result;
        }


        public static long compareDate(Date start, Date end) {
            long temp = 0;
            Calendar starts = Calendar.getInstance();
            Calendar ends = Calendar.getInstance();
            starts.setTime(start);
            ends.setTime(end);
            temp = ends.getTime().getTime() - starts.getTime().getTime();
            return temp;
        }


        /**
         * Transfer Date to another Date with new format
         *
         * @param date      Date
         * @param formatStr format of date
         * @return Date
         */
        public static Date dateToDate(Date date, String formatStr) {
            SimpleDateFormat myFmt0 = new SimpleDateFormat(formatStr);
            if (date == null || formatStr == null) {
                return null;
            }
            return format(myFmt0.format(date));
        }

        public static String dateToStr(Date date, String formatStr) {
            if (date == null || formatStr == null) {
                return null;
            }
            SimpleDateFormat myFmt0 = new SimpleDateFormat(formatStr);
            return myFmt0.format(date);
        }


        public static String getTime() {
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

            Calendar calendar = Calendar.getInstance();
            return format.format(calendar.getTime());
        }

        public static String getBeforeTime() {
            SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return format.format(calendar.getTime());
        }

        public static String getDateNow() {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
            return sdf.format(new Date());
        }


        public static String getDateStr(String format) {
            Calendar c = Calendar.getInstance();
            Date date = c.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }


        public static Date parseDate(String dateStr) {
            return parseDate(dateStr, DATE_FORMAT_YYYY_MM_DD);
        }

        public static Date parseDateTime(String dateStr) {
            return parseDate(dateStr, DEFAULT_DATE_FORMAT);
        }

        public static Date parseDate(String dateStr, String pattern) {
            if (dateStr == null || pattern == null) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {

            }
            return null;
        }

        public static Date add(int n) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, n);
            return calendar.getTime();
        }

        public static Date minus(int n) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -n);
            return calendar.getTime();
        }


        public static Date getNow() {
            return Calendar.getInstance().getTime();
        }


        public static String getFullNow() {
            return format(getNow());
        }


        /**
         * @param timeStamp type timeStamp
         * @param format    format of date
         * @return String
         */
        public static String parseToDate(String timeStamp, String format) {
            if (timeStamp == null || timeStamp.length() == 0) {
                return null;
            }
            if (format == null || format.length() == 0) {
                format = DEFAULT_DATE_FORMAT;
            }

            SimpleDateFormat sdFormat = new SimpleDateFormat(format);
            Long time = Long.valueOf(timeStamp);
            return sdFormat.format(time);
        }

        /**
         * <p>compare between date = firstDate plus Y seconds and secondDate</p>
         * <p>return true if date after secondDate</p>
         *
         * @param firstDate  Date
         * @param secondDate Date
         * @param seconds    long
         * @return boolean
         */
        public static boolean compareDate(Date firstDate, Date secondDate, long seconds) {
            if (firstDate == null || secondDate == null) {
                return false;
            }
            LocalDateTime firstLocalDateTime = firstDate.toInstant()
                .atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime();
            firstLocalDateTime = firstLocalDateTime.plusSeconds(seconds);
            LocalDateTime secondLocalDateTime = secondDate.toInstant()
                .atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime();
            return firstLocalDateTime.isAfter(secondLocalDateTime);
        }

        public static Date getStartOfDayFromDate(Date input) {
            LocalDateTime startOfDay = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault()).with(LocalTime.MIN);
            return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }

        public static Date getEndOfDayFromDate(Date input) {
            LocalDateTime endOfDay = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault()).with(LocalTime.MAX);
            return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }

        public static Date minusMonths(int n, Date source) {
            LocalDateTime dateTime = LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
            dateTime = dateTime.minusMonths(n);
            return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        public static LocalDate toLocalDate(Date source) {
            return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        public static String format(LocalDate source) {
            return format(source, DEFAULT_DATE_FORMAT);
        }

        public static String format(LocalDate source, String patten) {
            return source.format(DateTimeFormatter.ofPattern(patten));
        }

        public static boolean isOneBetweenTwoOthers(LocalDate one, LocalDate other1, LocalDate other2) {
            return other1.compareTo(one) * one.compareTo(other2) > 0;
        }

        public static LocalDateTime longToLocalDateTime(Long timestamp) {
            LocalDateTime triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                TimeZone.getDefault().toZoneId());
            return triggerTime;
        }

        public static boolean dateCheck(String dateStr, String pattern) {
            if (dateStr == null || pattern == null) {
                return false;
            }
            SimpleDateFormat dateFormat;
            dateFormat = new SimpleDateFormat(pattern);

            if (dateStr.trim().length() != dateFormat.toPattern().length()) {
                return false;
            }
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(dateStr.trim());
            } catch (ParseException pe) {
                return false;
            }
            return true;
        }

        public static Date addDays(Date date, int days) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days);
            return cal.getTime();
        }
    }

    public class LeapYearConstant {

      public final static   String DATE_28_02 = "28/02";

     public final static    String DATE_29_02 = "29/02";

    }

}
