package com.example.currencyconverter.CurrencyActions;

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

    public Currency getByShortname(String shortName)
    {
        for (int i =0; i < myCurrencies.size(); i++)
        {
            if (myCurrencies.get(i).getCharCode().equals(shortName)) return myCurrencies.get(i);
        }
        return null;
    }

    public boolean isEmpty()
    {
        return myCurrencies.size() == 1;
    }

    public String[] infoToString()
    {
        String[] result = new String[myCurrencies.size()];
        //if (myCurrencies == null) return new String[] {"Без интернета невозможно получение справки"};
        for (int i = 0; i < myCurrencies.size(); i++)
        {
            result[i] = myCurrencies.get(i).getCharCode() + " - " + myCurrencies.get(i).getName();
        }
        return result;
    }
}
