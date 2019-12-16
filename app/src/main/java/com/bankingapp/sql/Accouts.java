package com.bankingapp.sql;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Users.class,
        parentColumns = "id",
        childColumns = "userid"),
        indices = {@Index("userid")})
public class Accouts {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String accountnumber;
    public int balance;
    public int userid;

    public Accouts()
    {

    }

    @Override
    public String toString() {
        return accountnumber;
    }

}
