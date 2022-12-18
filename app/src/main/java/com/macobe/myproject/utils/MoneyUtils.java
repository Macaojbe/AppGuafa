package com.macobe.myproject.utils;

import java.text.DecimalFormat;

public class MoneyUtils {

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        formatter.setMaximumFractionDigits(2);
        return "$"+formatter.format(Double.parseDouble(amount));
    }

    public static String currencyFormat(double amount){
        return currencyFormat(String.valueOf(amount));
    }
}
