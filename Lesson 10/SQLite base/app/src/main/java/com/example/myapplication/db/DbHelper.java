package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "some_db.db";
    static final int VERSION = 1;
    public static final String PEOPLE_TABLE = "people";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_AGE = "age";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE People (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FIRST_NAME + " TEXT," +
                COLUMN_LAST_NAME + " TEXT," +
                COLUMN_AGE + " INTEGER" +
                ")");

        db.execSQL("INSERT INTO " + PEOPLE_TABLE + " (" + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ", " + COLUMN_AGE + ") VALUES ('Vasya', 'Pupkin', 30)");
        db.execSQL("INSERT INTO " + PEOPLE_TABLE + " (" + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ", " + COLUMN_AGE + ") VALUES ('Masha', 'Ivanova', 27)");
        db.execSQL("INSERT INTO " + PEOPLE_TABLE + " (" + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ", " + COLUMN_AGE + ") VALUES ('Petya', 'Sidorov', 22)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PEOPLE_TABLE);
        onCreate(db);
    }
}
