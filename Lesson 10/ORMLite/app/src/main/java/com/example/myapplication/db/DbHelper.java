package com.example.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.db.models.PersonModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper  {
    private static final String DATABASE_NAME = "some_db";
    private static final int DATABASE_VERSION = 1;

    private Dao<PersonModel, Long> peopleDao;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, PersonModel.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            if(checkTableExist(database,"people"))
                TableUtils.dropTable(connectionSource, PersonModel.class,false);

            onCreate(database,connectionSource);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private boolean checkTableExist(SQLiteDatabase database, String tableName){
        Cursor c = null;
        boolean tableExist = false;
        try {
            c = database.query(tableName, null,null,null,null,null,null);
            tableExist = true;
        }catch (Exception e){

        }
        return tableExist;
    }

    public Dao<PersonModel, Long> getUserItemDao() throws SQLException{
        if(peopleDao == null){
            peopleDao = getDao(PersonModel.class);
        }
        return peopleDao;
    }

    @Override
    public void close() {
        peopleDao = null;
        super.close();
    }

    public void clearTable() throws SQLException{
        TableUtils.clearTable(getConnectionSource(), PersonModel.class);
    }
}
