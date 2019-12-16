package com.bankingapp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bankingapp.sql.Accouts;

@Dao
public interface AccountDao {
    @Insert
    void insert(Accouts... accout);

    @Update
    void update(Accouts... accout);


    @Query("Select * FROM accouts")
    Accouts[] loadAllAccouts();

    @Query("Select * FROM accouts WHERE Accountnumber == :accoutnumber")
    Accouts loadAccout(String accoutnumber);

    @Query("Select * FROM accouts WHERE userid == :userid")
    Accouts[] getAccoutList(int userid);

}
