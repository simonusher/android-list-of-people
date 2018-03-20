package com.a235040.listofpeople;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
/**
 * Created by Szymon on 20.03.2018.
 */

public class UtilsTest {
    @Test
    public void stringNotEmpty_should_return_false_when_null() {
        String testString = null;
        assertEquals(Utils.stringNotEmpty(testString), false);
    }

    @Test
    public void stringNotEmpty_should_return_false_when_empty() {
        String testString = "";
        assertEquals(Utils.stringNotEmpty(testString), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseDate_should_throw_exception_when_string_empty() {
        String testString = "";
        Utils.parseDate(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseDate_should_throw_exception_when_string_null() {
        String testString = null;
        Utils.parseDate(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseDate_should_throw_exception_when_format_incorrect() {
        String testString = "10-08-2000";
        Utils.parseDate(testString);
    }

    @Test
    public void parseDate_should_throw_work_correctly_when_format_correct() {
        String testString = "10.08.2000";
        Utils.parseDate(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDifferenceInYearsFromNow_should_throw_exception_when_date_from_the_future() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        Utils.getDifferenceInYearsFromNow(calendar.getTime());
    }

    @Test
    public void getDifferenceInYearsFromNow_should_work_when_date_equals_now() {
        Date now = new Date();
        int diff = Utils.getDifferenceInYearsFromNow(now);
        System.out.println("Difference from now is: " + diff);
    }

    @Test
    public void getDifferenceInYearsFromNow_should_work_when_date_before_now() {
        Date date = Utils.parseDate("10.08.2000");
        int diff = Utils.getDifferenceInYearsFromNow(date);
        System.out.println("Difference from 10.08.2000 is: " + diff);
    }
}
