package com.example.currencyconverter;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

public class Request extends AsyncTask<String, Integer, CurrencyStore >{

    @Override
    protected CurrencyStore doInBackground(String... arg)  {


        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req="+arg[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "windows-1251"));
                String answer = "";
                String line = null;

                while ((line = reader.readLine()) != null) {
                    if (line != null) answer += line;
                }

                reader.close();
                answer = answer.replace(" encoding=\"windows-1251\""," encoding=\"utf-8\"");
                Charset cset = Charset.forName("UTF-8");
                ByteBuffer buf = cset.encode(answer);
                byte[] b = buf.array();
                String str = new String(b);

                return Parcer.parse(str);
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(CurrencyStore s) {
        super.onPostExecute(s);

    }
}