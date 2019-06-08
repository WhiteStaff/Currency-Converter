package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] helpdata = null;
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final EditText et = findViewById(R.id.valuteInput);
        final TextView out = findViewById(R.id.valuteOutput);
        TextView to = findViewById(R.id.to);
        CalendarView calendar = findViewById(R.id.calendarView);
        Spinner inputValute = findViewById(R.id.spinner);
        final Spinner outputValute = findViewById(R.id.spinner4);
        Button button = findViewById(R.id.button);
        final int[] positions = {0, 0};
        final String[] date = {format.format(new Date())};
        ;

        try {
            helpdata = new Request().execute(format.format(new Date())).get().getCurrenciesShortnames();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String[] data = helpdata;

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String day, month;
                day = Integer.toString(i2);
                month = Integer.toString(i1 + 1);
                if (i2 < 10) day = "0" + i2;
                if (i1 < 10) month = "0" + (i1 + 1);
                date[0] = day + "/" + month + "/" + i;
            }
        });

        // адаптер
        ArrayAdapter<String> inputAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        inputValute.setAdapter(inputAdapter);
        // заголовок
        inputValute.setPrompt("Title");
        // выделяем элемент
        inputValute.setSelection(2);
        // устанавливаем обработчик нажатия
        inputValute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                positions[0] = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        // адаптер
        ArrayAdapter<String> outputAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        outputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        outputValute.setAdapter(outputAdapter);
        // заголовок
        outputValute.setPrompt("Title");
        // выделяем элемент
        outputValute.setSelection(2);
        // устанавливаем обработчик нажатия
        outputValute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                positions[1] = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CurrencyStore help = new Request().execute(date[0]).get();
                    if (help.isEmpty()) {
                        out.setText("Что-то пошло не так");
                        return;
                    }
                    if (help.getByShortname(data[positions[0]]) == null ||
                            (help.getByShortname(data[positions[1]]) == null))
                    {
                        out.setText("Цб не знал, ой");
                        return;
                    }
                    double from = help.getByShortname(data[positions[0]]).getExchangeRate();
                    double to = help.getByShortname(data[positions[1]]).getExchangeRate();
                    String valuteToConvert = et.getText().toString();
                    if (valuteToConvert.equals("")) valuteToConvert = "0";
                    double count = Double.parseDouble(valuteToConvert);
                    out.setText(CurrencyCalculator.Calculate(
                            from, to, count));

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}


