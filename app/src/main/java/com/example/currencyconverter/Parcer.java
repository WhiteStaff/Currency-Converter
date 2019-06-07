package com.example.currencyconverter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Parcer {
    private String text;

    /*public Parcer(String text)
    {
        this.text = text;
    }*/

    public static List<Valute> parce(String a)
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        List<Valute> valuteList = new ArrayList<Valute>();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(a)));
            NodeList valutes = doc.getFirstChild().getChildNodes();

            for(int i = 0; i < valutes.getLength(); i++)
            {

                NodeList children = valutes.item(i).getChildNodes();
                Valute current = new Valute(children.item(0).getFirstChild().getNodeValue()
                        ,children.item(1).getFirstChild().getNodeValue()
                        ,Integer.parseInt(children.item(2).getFirstChild().getNodeValue())
                        ,children.item(3).getFirstChild().getNodeValue()
                        ,Double.parseDouble(children.item(4).getFirstChild().getNodeValue().replace(",", ".")));

                valuteList.add(current);
            }

            return valuteList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}