package com.bankingapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bankingapp.dao.AccountDao;
import com.bankingapp.dao.BillDao;
import com.bankingapp.dao.UserDao;
import com.bankingapp.sql.Accouts;
import com.bankingapp.sql.Bill;
import com.bankingapp.sql.Users;

@Database(entities = {Users.class, Accouts.class, Bill.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;


    public abstract AccountDao accountDao();
    public abstract UserDao userDao();
    public abstract BillDao billDao();


    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "canada-bank-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


}
