package com.example.currencyconverter;

import java.util.List;

public class CurrencyStore {
    private List<Currency> myCurrencies;

    public CurrencyStore(List<Currency> currencies)
    {
        myCurrencies = currencies;
    }

    public String[] getCurrenciesShortnames()
    {
        String [] result = new  String[myCurrencies.size()];
        for (int i = 0; i < myCurrencies.size(); i++ )
        {
            result[i] = myCurrencies.get(i).getCharCode();
        }
        return result;
    }

    public double getExchangeRate(int i)
    {
        return myCurrencies.get(i).getRate()/myCurrencies.get(i).getCount();
    }
}