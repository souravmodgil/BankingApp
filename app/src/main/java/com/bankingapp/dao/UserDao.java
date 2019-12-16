package com.bankingapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.bankingapp.sql.Users;
@Dao
public interface UserDao {

    @Insert
    void insert(Users... users);



    @Query("Select * FROM user")
    Users[] loadAll();

    @Query("SELECT * FROM user WHERE adminCardNo = :admincardNo AND "
            + "pincode = :pincode ")
    Users isLogin(String admincardNo, String pincode);
}
