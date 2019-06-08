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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] helpdata = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            helpdata = new Request().execute(format.format(new Date())).get().getCurrenciesShortnames();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String[] data = helpdata;


        EditText et = findViewById(R.id.valuteInput);
        TextView out = findViewById(R.id.valuteOutput);
        TextView to = findViewById(R.id.to);
        CalendarView calendar = findViewById(R.id.calendarView);
        final String[] date = {null};

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String day, month;
                day = Integer.toString(i2);
                month = Integer.toString(i1);
                if (i2<10) day = "0"+i2;
                if (i1<10) month = "0"+i1;
                date[0] = day+"/"+month+"/"+i;


            }
        });



        // адаптер
        ArrayAdapter<String> inputAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final int[] positions = {0, 0};

        Spinner inputValute = (Spinner) findViewById(R.id.spinner);

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

        Spinner outputValute = (Spinner) findViewById(R.id.spinner4);
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

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this, positions[0]+" и "+positions[1]+" и "+date[0], Toast.LENGTH_SHORT).show();
            }
        });

    }
}


