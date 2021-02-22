package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.models.Person;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnOpenActivity, btnShowToast, btnShowSnackBar, btnEditPerson, btnAddPerson;
    ListView lvPeople;

    final int EDIT_CODE = 1;

    static List<Person> people = new ArrayList<>();
    static  {
        people.add(new Person(1, "Vasya", "Pupkin"));
        people.add(new Person(2, "Petya", "Stepanov"));
        people.add(new Person(3, "Masha", "Ivanova"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenActivity = findViewById(R.id.btnOpenActivity);
        btnShowSnackBar = findViewById(R.id.btnShowSnackBar);
        btnShowToast = findViewById(R.id.btnShowToast);
        btnEditPerson = findViewById(R.id.btnEditPerson);
        lvPeople = findViewById(R.id.lvPeople);
        btnAddPerson = findViewById(R.id.btnAddPerson);

        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(Person.class.getSimpleName(), people.get(0));
                startActivity(intent);
            }
        });

        btnShowSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello world", Snackbar.LENGTH_LONG).show();
            }
        });

        btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "Hello world",  Toast.LENGTH_LONG);
                toast.setGravity(Gravity.END, 0, 0);
                toast.show();
            }
        });

        btnEditPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(Person.class.getSimpleName(), people.get(0));
                startActivityForResult(intent, EDIT_CODE);
            }
        });

        final ArrayAdapter<Person> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, people);
        lvPeople.setAdapter(adapter);

        lvPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, people.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(new Person(1, "1", "1"));
                //people.get(0).setFirstName("Demo");
                //adapter.notifyDataSetChanged();
            }
        });
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {
            case EDIT_CODE:
                switch(resultCode) {
                    case RESULT_OK:
                        Toast.makeText(this, "Ok: " + data.getExtras().getString("message"), Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}