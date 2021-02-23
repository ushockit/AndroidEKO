package com.example.myapplication.db.managers;

import android.content.Context;

import com.example.myapplication.db.DbHelper;
import com.example.myapplication.db.models.PersonModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

public class PeopleDbManager {

    private final Context mContext;
    private static PeopleDbManager INSTANCE;
    private DbHelper databaseHelper;

    private Dao<PersonModel, Long> peopleDao;
    private static String FIRST_NAME = "firstName";
    private static String LAST_NAME = "firstName";
    private static String AGE = "age";
    private static String ID = "id";

    public PeopleDbManager(Context mContext) {
        this.mContext = mContext;
        databaseHelper = OpenHelperManager.getHelper(mContext, DbHelper.class);

        try {
            peopleDao = databaseHelper.getUserItemDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static PeopleDbManager getInstance(Context context){
        if(INSTANCE == null) INSTANCE = new PeopleDbManager(context);
        return INSTANCE;
    }
    public void releaseDB(){
        if (databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
            INSTANCE = null;
        }
    }

    public int clearAllData(){
        try {
            if (databaseHelper == null) return -1;
            databaseHelper.clearTable();
            return 0;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public List<PersonModel> getAllUsers(){
        try {
            if (peopleDao == null)return null;
            return peopleDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public int insertUserItem(PersonModel personModel){
        try {
            UpdateBuilder updateBuilder = peopleDao.updateBuilder();
            String firstName = personModel.getFirstName() != null ? personModel.getFirstName(): "";
            int age = personModel.getAge();

            if(peopleDao == null) return -1;
            peopleDao.create(personModel);
            return 1;
        }catch (SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }
}
