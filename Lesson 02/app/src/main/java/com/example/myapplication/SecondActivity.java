package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


    TextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvValue = findViewById(R.id.tvValue);

        //getIntent - получает Intent, которым был запущен текущий Activity
        Bundle extras = getIntent().getExtras();

        String value = extras.getString("value");
        tvValue.setText(value);
    }
}