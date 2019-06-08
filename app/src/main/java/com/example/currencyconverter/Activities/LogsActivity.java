package com.example.currencyconverter.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.Logs.Log;
import com.example.currencyconverter.Logs.LogsOperations;
import com.example.currencyconverter.R;

import java.util.ArrayList;
import java.util.List;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        List<TextView> logViews = new ArrayList<>();

        TextView log0 = findViewById(R.id.textView4);
        TextView log1 = findViewById(R.id.textView2);
        TextView log2 = findViewById(R.id.textView);
        TextView log3 = findViewById(R.id.textView3);
        TextView log4 = findViewById(R.id.textView7);
        TextView log5 = findViewById(R.id.textView6);
        TextView log6 = findViewById(R.id.textView8);
        TextView log7 = findViewById(R.id.textView9);
        TextView log8 = findViewById(R.id.textView5);
        TextView log9 = findViewById(R.id.textView10);

        logViews.add(log0);
        logViews.add(log1);
        logViews.add(log2);
        logViews.add(log3);
        logViews.add(log4);
        logViews.add(log5);
        logViews.add(log6);
        logViews.add(log7);
        logViews.add(log8);
        logViews.add(log9);

        List<Log> logs = LogsOperations.getLogs(getApplicationContext()).getLogs();
        if (logs.size() != 0) {
            int count = logs.size();
            if (logs.size() > 10) count = 10;
            for (int i = 0; i < count; i++) {
                logViews.get(i).setText(logs.get(i).toString());
            }
        }

    }
}
