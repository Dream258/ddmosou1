package com.xxx.common.util;

import java.text.NumberFormat;
import java.util.Locale;

public class DoubleUtils {

    public static String localeChina(double number){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return format.format(number);
    }

}
