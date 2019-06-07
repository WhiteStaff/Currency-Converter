package com.example.currencyconverter;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class Request extends AsyncTask<String, Integer, String>{

    @Override
    protected String doInBackground(String... arg)  {


        try {
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=22/03/2019");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return "s";
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