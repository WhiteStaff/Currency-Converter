package com.example.currencyconverter;

public class Currency {
    private String code;
    private String charCode;
    private Integer count;
    private String name;
    private double exchangeRate;

    public Currency(String code, String charCode, Integer count, String name, double exchangeRate)
    {
        this.code = code;
        this.charCode = charCode;
        this.count = count;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public String getCharCode()
    {
        return charCode;
    }

    public int getCount()
    {
        return count;
    }

    public double getRate()
    {
        return exchangeRate;
    }
}
