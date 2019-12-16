package com.bankingapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.bankingapp.sql.Bill;
import com.bankingapp.sql.Users;
@Dao
public interface BillDao {
    @Insert
    void insert(Bill... bill);



    @Query("Select * FROM bill")
    Users[] loadAll();
}
