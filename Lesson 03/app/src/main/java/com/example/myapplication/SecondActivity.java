package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.models.Person;
import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity {

    Button btnShowPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShowPerson = findViewById(R.id.btnShowPerson);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            final Person person = (Person) bundle.getSerializable(Person.class.getSimpleName());
            btnShowPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, person.toString(), Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }
}