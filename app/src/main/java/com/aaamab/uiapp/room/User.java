package com.aaamab.uiapp.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    public int userId ;


    @ColumnInfo(name = "first_name")
    public String first_name ;


    @ColumnInfo(name = "last_name")
    public String last_name ;




}
