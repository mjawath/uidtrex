/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import java.util.Random;

/**
 *
 * @author d
 */
public class SystemUtil {

    public static  String getKeys() {
        String rn=""+AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis()+"-"+rn;
        return key;
    }
    public static String getKeyStr() {
    return randomString(15);
    }
    static  String  randomString(int len) {

//        Calendar calendar = Calendar.getInstance();
//        StringBuilder sb = new StringBuilder(len);
//        for (int i = 0; i < len; i++) {
//            sb.append(AB.charAt(rnd.nextInt(AB.length())));
//        }
//        sb.append("-").append(calendar.getTimeInMillis() + "" + AB.charAt(rnd.nextInt(AB.length())));
//        return sb.toString();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
   
    public String getKey(String shopName) {

        String rn=""+AB.charAt(rnd.nextInt(AB.length()));
        String rn2=""+AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis()+"-"+rn+rn2;

// / create a java calendar instance


// get a java.util.Date from the calendar instance.
// this date will represent the current instant, or "now".
//java.util.Date now = calendar.getTime();

// a java current time (now) instance
//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());


        //java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());


        return key;
    }

    public String getKey() {

        String rn=""+AB.charAt(rnd.nextInt(AB.length()));
        String rn2=""+AB.charAt(rnd.nextInt(AB.length()));
        String key = System.currentTimeMillis()+"-"+rn+rn2;

// / create a java calendar instance


// get a java.util.Date from the calendar instance.
// this date will represent the current instant, or "now".
//java.util.Date now = calendar.getTime();

// a java current time (now) instance
//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());


        //java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());


        return key;
    }

    static final String AB = "GHIJBK013LO8L2MNOP7ECD456QRS9ABKFTUVWXYZ";
    static Random rnd = new Random();

}
