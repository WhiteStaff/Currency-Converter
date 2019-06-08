package com.example.currencyconverter;

public class CurrencyCalculator {
    private double from;
    private double to;
    private double count;

    public static String Calculate(double from, double to, double count)
    {
        return Double.toString (count*from/to);
    }
}
