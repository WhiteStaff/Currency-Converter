package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.DSAKey;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button first = findViewById(R.id.button);

        Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Request().execute();
            }
        });
    }
}


