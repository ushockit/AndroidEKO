package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.adapters.PeopleAdapter;
import com.example.myapplication.models.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PeopleAdapter.OnItemClickListener{
    static List<String> cities = new ArrayList<>();
    static List<Person> people = new ArrayList<>();
    static {
        cities.add("Днепр");
        cities.add("Киев");
        cities.add("Харьков");
        cities.add("Львов");
        cities.add("Житомир");
        cities.add("Запорожье");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            people.add(new Person("First name 1", "Last name 1", LocalDate.of(2000, 01, 05), R.drawable.person));
            people.add(new Person("First name 2", "Last name 2", LocalDate.of(2000, 01, 05), R.drawable.person));
            people.add(new Person("First name 3", "Last name 3", LocalDate.of(2000, 01, 05), R.drawable.person));
            people.add(new Person("First name 4", "Last name 4", LocalDate.of(2000, 01, 05), R.drawable.person));
            people.add(new Person("First name 5", "Last name 5", LocalDate.of(2000, 01, 05), R.drawable.person));
        }
    }

    ListView lvCities;
    RecyclerView rvPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCities = findViewById(R.id.lvCities);
        rvPeople = findViewById(R.id.rvPeople);

        //привязка данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        lvCities.setAdapter(adapter);

        lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        PeopleAdapter peopleAdapter = new PeopleAdapter(this, R.layout.person_list_item, people);
        rvPeople.setAdapter(peopleAdapter);
    }

    @Override
    public void onItemClick(Person person, int pos) {
        Toast.makeText(this, person.toString(), Toast.LENGTH_SHORT).show();
    }
}