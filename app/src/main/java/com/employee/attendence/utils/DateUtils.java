package com.employee.attendence.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.employee.attendence.appconstant.Constants;


/**
 * Created by user on 11/10/17.
 */

public class DateUtils implements Constants {

    public static String getDate(String dateString, String oldFormat, String newFormat) {
        DateFormat originalFormat = new SimpleDateFormat(oldFormat, Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat(newFormat);
        Date date = null;
        try {
            date = originalFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }


}
