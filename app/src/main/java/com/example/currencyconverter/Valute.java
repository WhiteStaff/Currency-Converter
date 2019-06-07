package com.example.currencyconverter;

public class Valute {
    private String code;
    private String charCode;
    private Integer count;
    private String name;
    private double exchangeRate;

    public Valute(String code, String charCode, Integer count, String name, double exchangeRate)
    {
        this.code = code;
        this.charCode = charCode;
        this.count = count;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

}
