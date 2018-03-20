package com.a235040.listofpeople;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.a235040.listofpeople.DateIncorrectException.DateErrorType.*;

/**
 * Created by Szymon on 20.03.2018.
 */

public abstract class Utils {
    private static final String EMPTY_STRING = "";
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final Calendar CALENDAR = new GregorianCalendar();
    static {
        CALENDAR.setLenient(false);
    }

    public static boolean stringNotEmpty(String string){
        return string != null && !string.equals(EMPTY_STRING);
    }

    public static Date parseDate(String dateString) {
        if(stringNotEmpty(dateString)){
            try{
                return DEFAULT_DATE_FORMAT.parse(dateString);
            }catch (ParseException e){
                throw new DateIncorrectException(DATE_FORMAT_ERROR);
            }
        }
        else throw new IllegalArgumentException("Date string empty or null!");
    }

    public static int getDifferenceInYearsFromNow(Date date){
        Date now = new Date();
        if(date.before(now) || date.equals(now)){
            CALENDAR.setTime(date);
            try{
                CALENDAR.getTime();
                int year = CALENDAR.get(Calendar.YEAR);
                CALENDAR.setTime(now);
                int yearNow = CALENDAR.get(Calendar.YEAR);
                return yearNow - year;
            }
            catch (Exception e){
                throw new DateIncorrectException(DATE_INCORRECT_ERROR);
            }
        } else {
            throw new DateIncorrectException(FUTURE_DATE_ERROR);
        }
    }

    public static int getDifferenceInYearsFromNow(String dateString){
        Date date = parseDate(dateString);
        return getDifferenceInYearsFromNow(date);
    }
}
