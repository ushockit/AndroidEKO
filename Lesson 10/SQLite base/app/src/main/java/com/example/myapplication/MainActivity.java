package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.myapplication.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ListView lvPeople;
    Cursor peopleCursor;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPeople = findViewById(R.id.lvPeople);

        dbHelper = new DbHelper(getApplicationContext());

        db = dbHelper.getReadableDatabase();
        peopleCursor = db.rawQuery("SELECT * FROM " + DbHelper.PEOPLE_TABLE, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                peopleCursor,
                new String[] { DbHelper.COLUMN_FIRST_NAME, DbHelper.COLUMN_LAST_NAME },
                new int[] { android.R.id.text1, android.R.id.text2 },
                0);
        lvPeople.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        peopleCursor.close();
        db.close();
    }
}