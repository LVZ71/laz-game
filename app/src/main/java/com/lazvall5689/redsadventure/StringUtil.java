package com.lazvall5689.redsadventure;

/**
 * Created by oscartorres on 10/27/17.
 */

public class StringUtil {
    public static int[] strToInt(String[] arr) {
        int[] ruts = new int[arr.length];
        for (int x = 0; x < arr.length; x++) {
            ruts[x] = parseInt(arr[x]);
        }
        return ruts;
    }

    public static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            // couldn't parse int, return -1 to notify
            return -1;
        }
    }

    public static String intArrToStr(int[] ints) {
        StringBuilder builder = new StringBuilder();
        for (int a : ints) {
            builder.append(a);
            builder.append(" ");
        }
        return builder.toString();
    }

}
