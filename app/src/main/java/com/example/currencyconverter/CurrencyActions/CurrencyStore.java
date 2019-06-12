package com.example.currencyconverter.CurrencyActions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CurrencyStore {
    private List<Currency> myCurrencies;
    private String date;
    private static NumberFormat formatter = new DecimalFormat("#0.0000");

    public CurrencyStore(List<Currency> currencies, String date) {
        myCurrencies = currencies;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String[] getCurrenciesShortnames() {
        String[] result = new String[myCurrencies.size()];
        for (int i = 0; i < myCurrencies.size(); i++) {
            result[i] = myCurrencies.get(i).getCharCode();
        }
        return result;
    }

    public Currency getByShortname(String shortName) {
        for (int i = 0; i < myCurrencies.size(); i++) {
            if (myCurrencies.get(i).getCharCode().equals(shortName)) return myCurrencies.get(i);
        }
        return null;
    }

    public boolean isEmpty() {
        return myCurrencies.size() == 1;
    }

    public String[] infoToString() {
        String[] result = new String[myCurrencies.size()];
        result[0] = "Курсы валют в рублях на " + date + ":";
        for (int i = 1; i < myCurrencies.size(); i++) {
            result[i] = myCurrencies.get(i).getCharCode() + "(" +
                    myCurrencies.get(i).getName() + ") = " +
                    formatter.format(myCurrencies.get(i).getExchangeRate());
        }
        return result;
    }
}
