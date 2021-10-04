package com.aaamab.uiapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;


@Dao
public interface EmployeeDAO {

    @Query("SELECT * FROM employee")
    ArrayList<User> getAllUsers();


    @Query("SELECT * FROM employee WHERE eId IN (:ids)")
    ArrayList<User> getUsersById(int[] ids);


    @Query("SELECT * FROM employee WHERE first LIKE :first  LIMIT 1")
    User getUserByName(String first  );


    @Insert
    void insertAll(Employee... employees);

    @Delete
    void delete(Employee employees);
}
