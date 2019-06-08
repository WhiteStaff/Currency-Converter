package com.example.currencyconverter.CurrencyActions;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CurrencyCalculator {
    private static NumberFormat  formatter = new DecimalFormat("#0.0000");

    public static String Calculate(double from, double to, double count)
    {
        return formatter.format(count*from/to);
    }
}
