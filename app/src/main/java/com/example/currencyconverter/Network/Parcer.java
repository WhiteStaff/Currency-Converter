package com.example.currencyconverter.Network;

import com.example.currencyconverter.CurrencyActions.Currency;
import com.example.currencyconverter.CurrencyActions.CurrencyStore;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Parcer {

    public static CurrencyStore parse(String a)
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<Currency> currencyList = new ArrayList<Currency>();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(a)));
            NodeList valutes = doc.getFirstChild().getChildNodes();

            for(int i = 0; i < valutes.getLength(); i++)
            {

                NodeList children = valutes.item(i).getChildNodes();
                Currency current = new Currency(children.item(0).getFirstChild().getNodeValue()
                        ,children.item(1).getFirstChild().getNodeValue()
                        ,Integer.parseInt(children.item(2).getFirstChild().getNodeValue())
                        ,children.item(3).getFirstChild().getNodeValue()
                        ,Double.parseDouble(children.item(4).getFirstChild().getNodeValue().replace(",", ".")));

                currencyList.add(current);
            }
            currencyList.add(new Currency("000", "RUB", 1, "Рубли", 1.0));


            return new CurrencyStore(currencyList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}