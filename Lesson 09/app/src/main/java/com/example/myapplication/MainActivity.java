package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.models.AppSettings;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    final String APP_SETTINGS = "settings";
    final String APP_COUNTER = "counter";
    final String APP_TEXT = "text";

    SharedPreferences prefs;

    EditText etText;
    Button btnCounter, btnShowSettings;
    TextView tvText;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);
        btnCounter = findViewById(R.id.btnCounter);
        tvText = findViewById(R.id.tvText);
        btnShowSettings = findViewById(R.id.btnShowSettings);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++counter;
                tvText.setText(String.valueOf(counter));
            }
        });

        btnShowSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


        //получение настроек
        prefs = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);

//        SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
//            @Override
//            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//
//            }
//        };
//
        SharedPreferences defPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        defPrefs.registerOnSharedPreferenceChangeListener(this);


    }

    @Override
    protected void onPause() {
        super.onPause();

        //словарь
        SharedPreferences.Editor editor = prefs.edit();
        //добавление данных в словарь
        editor.putInt(APP_COUNTER, counter);
        editor.putString(APP_TEXT, etText.getText().toString());
        //применение настроек
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.contains(APP_COUNTER)) {
            tvText.setText(String.valueOf(prefs.getInt(APP_COUNTER, 0)));
        }

        if (prefs.contains(APP_TEXT)) {
            etText.setText(prefs.getString(APP_TEXT, ""));
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        AppSettings.NOTIFICATIONS = sharedPreferences.getBoolean(key, AppSettings.NOTIFICATIONS);
    }
}