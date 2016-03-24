package uo.sdi.entrega1.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static Date parseDate(String str) throws ParseException {
        return dateFormat.parse(str);
    }

    public static String formatDate(String date) {
        Date finalDate = null;
        try {
            finalDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException ignored) {
        }
        return dateFormat.format(finalDate);
    }
    
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }
}
