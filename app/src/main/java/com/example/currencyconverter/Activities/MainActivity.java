package com.example.currencyconverter.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.CurrencyActions.CurrencyCalculator;
import com.example.currencyconverter.CurrencyActions.CurrencyStore;
import com.example.currencyconverter.Logs.Log;
import com.example.currencyconverter.Logs.LogsOperations;
import com.example.currencyconverter.Network.Request;
import com.example.currencyconverter.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private CurrencyStore store = null;
    private String courseDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] helpdata = null;
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final EditText et = findViewById(R.id.valuteInput);
        et.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        final TextView out = findViewById(R.id.valuteOutput);
        TextView to = findViewById(R.id.to);
        CalendarView calendar = findViewById(R.id.calendarView);
        Spinner inputValute = findViewById(R.id.spinner);
        final Spinner outputValute = findViewById(R.id.spinner4);
        Button button = findViewById(R.id.button);
        final int[] positions = {0, 0};
        final String[] date = {format.format(new Date())};

        if (!hasConnection(getApplicationContext()))
        {
            out.setText("Нет интернета");
            out.setTextColor(Color.RED);
            return;
        } else
        try {
            courseDate = format.format(new Date());
            store = new Request().execute(courseDate).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        helpdata = store.getCurrenciesShortnames();
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
                courseDate = date[0];
            }
        });

        // адаптер
        ArrayAdapter<String> inputAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        inputValute.setAdapter(inputAdapter);
        // заголовок
        inputValute.setPrompt("From");
        // выделяем элемент
        inputValute.setSelection(0);
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
        outputValute.setPrompt("To");
        // выделяем элемент
        outputValute.setSelection(0);
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
                CurrencyStore help = null;
                if (!hasConnection(getApplicationContext()))
                {
                    out.setText("Нет интернета");
                    out.setTextColor(Color.RED);
                    return;
                } else {
                    try {
                        help = new Request().execute(date[0]).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (help.isEmpty()) {
                        out.setText("Нет курса");
                        return;
                    }
                    if (help.getByShortname(data[positions[0]]) == null ||
                            (help.getByShortname(data[positions[1]]) == null)) {
                        out.setText("Цб не знал, ой");
                        return;
                    }
                    double from = help.getByShortname(data[positions[0]]).getExchangeRate();
                    double to = help.getByShortname(data[positions[1]]).getExchangeRate();
                    String valuteToConvert = et.getText().toString();
                    if (valuteToConvert.equals("")) valuteToConvert = "0";
                    double count = 0.0;
                    try {
                        count = Double.parseDouble(valuteToConvert);
                    } catch (Exception e) {
                        out.setText("Введи число");
                        return;
                    }

                    String result = CurrencyCalculator.Calculate(
                            from, to, count);
                    out.setText(result);
                    Log log = new Log(count, data[positions[0]], data[positions[1]], result, date[0]);
                    LogsOperations.addNewLog(log, getApplicationContext());
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.info:
                Intent intent1 = new Intent(getApplicationContext(), InfoActivity.class);
                try {
                    store = new Request().execute(courseDate).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (store == null)
                intent1.putExtra("data", new String[]{"Без интернета невозможно получение справки"});
            else {
                intent1.putExtra("data", store.infoToString());
                LogsOperations.addNewLog(new Log(0.0, null, null,
                        null, store.getDate()), getApplicationContext());
            }
                startActivity(intent1);
                return true;

            case R.id.log:
                Intent intent = new Intent(getApplicationContext(), LogsActivity.class);
                startActivity(intent);
                return true;
            case R.id.reboot:
                Intent mStartActivity = new Intent(getApplicationContext(), MainActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10, mPendingIntent);
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}


