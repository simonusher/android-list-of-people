package com.a235040.listofpeople;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Szymon on 20.03.2018.
 */

public abstract class Utils {
    private static final String EMPTY_STRING = "";
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final int DAYS_IN_YEAR = 365;
    private static final Pattern namePattern = Pattern.compile("\\p{L}+");
    static {
        CALENDAR.setTime(new Date());
    }

    public static boolean stringNotEmpty(String string){
        return string != null && !string.equals(EMPTY_STRING);
    }

    public static String formatDate(Date date){
        return DEFAULT_DATE_FORMAT.format(date);
    }

    public static String formatDate(Calendar calendar){
        return formatDate(calendar.getTime());
    }

    public static int getDifferenceInYearsFromNow(Date date){
        Date now = new Date();
        if(date.before(now) || date.equals(now)){
            return getDateDiffYears(date, now);
        } else {
            throw new IllegalArgumentException("Date can't be from the future!");
        }
    }

    public static int getDateDiffYears(Date earlier, Date later){
        long diffInMilis = later.getTime() - earlier.getTime();
        int days = (int)TimeUnit.DAYS.convert(diffInMilis, TimeUnit.MILLISECONDS);
        return days / DAYS_IN_YEAR;
    }

    public static boolean isAlphabetic(String string){
        Matcher matcher = namePattern.matcher(string);
        return matcher.matches();
    }
}
