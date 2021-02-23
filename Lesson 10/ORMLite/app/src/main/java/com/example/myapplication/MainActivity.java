package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.db.managers.PeopleDbManager;
import com.example.myapplication.db.models.PersonModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonModel newPerson = new PersonModel();
        newPerson.setFirstName("New first name");
        newPerson.setLastName("New last name");
        newPerson.setAge(20);

        //int res = PeopleDbManager.getInstance(getApplicationContext()).insertUserItem(newPerson);

        List<PersonModel> people =  PeopleDbManager.getInstance(getApplicationContext()).getAllUsers();
        int a = 0;
    }
}