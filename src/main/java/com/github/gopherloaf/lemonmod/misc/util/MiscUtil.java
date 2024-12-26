package com.github.gopherloaf.lemonmod.misc.util;

public class MiscUtil {
    public static double roughRounding(double number, int decimals){
        return Math.round(number * Math.pow(10, decimals)) / Math.pow(10, decimals);
    }

    public static float roughRounding(float number, int decimals){
        return (float) (Math.round(number * Math.pow(10, decimals)) / Math.pow(10, decimals));
    }
}
