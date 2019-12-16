package com.bankingapp.sql;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "bill")
public class Bill {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String ulitilitName;
    public int charges ;

    public Bill()
    {

    }

}
