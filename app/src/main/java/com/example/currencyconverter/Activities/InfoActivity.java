package com.example.currencyconverter.Activities;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        String[] data = getIntent().getStringArrayExtra("data");
        TextView view = findViewById(R.id.infoView);
        view.setMovementMethod(new ScrollingMovementMethod());
        String info = "";
        for (int i = 0; i < data.length; i++) {
            info += data[i] + "\n";
        }
        view.setText(info);
    }
}
