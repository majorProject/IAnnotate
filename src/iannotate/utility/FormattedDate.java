/*
 * 
 */
package iannotate.utility;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author susan
 */
public class FormattedDate {
    /**
     * When the input is 2011:12:11,
     * then this function gives output as:
     *  2011 December 11
     * @param unformattedDate
     * @return formatted date 
     */
    static public String getDate(String unformattedDate){
        String[] month = unformattedDate.split(":");
        String result;
        int dat = Integer.parseInt(month[1]);
        Calendar a = Calendar.getInstance();
        a.set(Calendar.MONTH, dat-1);
        a.set(Calendar.DAY_OF_MONTH,1);
        String nameOfMonth = a.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        result = month[0]+" "+nameOfMonth+" "+month[2];
        return result;
    }
}
