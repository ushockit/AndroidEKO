package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDemo, btnGlobal;
    TextView tvFirst, tvSecond;
    EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //получение ссылок на элементы управления
        btnDemo = findViewById(R.id.btnDemo);
        btnGlobal = findViewById(R.id.btnGlobal);
        tvFirst = findViewById(R.id.textView);
        tvSecond = findViewById(R.id.textView2);
        etText = findViewById(R.id.etText);

        View.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFirst.setText("Hello Android!");
            }
        };

        btnDemo.setOnClickListener(btnClickListener);
        btnGlobal.setOnClickListener(this);


        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvFirst.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void someClickListener(View v) {
        tvSecond.setText("Hi!!!");
    }

    @Override
    public void onClick(View v) {
        tvFirst.setText("Text from global listener!!!");
    }
}