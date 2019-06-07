package com.example.currencyconverter;

public class Valute {
    private String code;
    private String charCode;
    private int count;
    private String name;
    private double exchangeRate;

    public Valute(String code, String charCode, int count, String name, double exchangeRate)
    {
        this.code = code;
        this.charCode = charCode;
        this.count = count;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

}
