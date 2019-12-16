package com.bankingapp.sql;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "user")
public class Users implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String UserName;
    public String pincode;
    public String adminCardNo;



    @Ignore
    public List<Accouts> accouts ;







}
