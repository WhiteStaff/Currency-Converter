package com.example.currencyconverter;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request extends AsyncTask<String, Integer, String>{

    @Override
    protected String doInBackground(String... arg)  {


        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=22/03/2019");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));
                String answer = "";
                String line = null;

                while ((line = reader.readLine()) != null) {
                    answer += line;
                }

                reader.close();


            } else {
                return "f";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "11";

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}