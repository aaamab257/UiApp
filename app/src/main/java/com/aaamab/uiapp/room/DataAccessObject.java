package com.aaamab.uiapp.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DataAccessObject {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();


    @Query("SELECT * FROM user WHERE userId IN (:ids)")
    List<User> getUsersById(int[] ids);


    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    User getUserByName(String first , String last );


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(User users);

    @Delete
    void delete(User user);



}
