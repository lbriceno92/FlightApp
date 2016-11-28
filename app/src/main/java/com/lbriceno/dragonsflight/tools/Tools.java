package com.lbriceno.dragonsflight.tools;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luis_ on 11/27/2016.
 */
public class Tools {

    public static String formatCurrency(double value) {
        return String.format("%.2f", value);
    }

    public static String format(String dateString, String timeString) {
        Date date = stringToDate(dateString, "mm/dd/yyyy");
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return timeString + df.format(" EEE., dd MMM.",date).toString();
    }

    private static Date stringToDate(String aDate, String aFormat) {
        if (aDate == null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}
