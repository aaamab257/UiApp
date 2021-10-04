package com.aaamab.uiapp.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey
    public int eId ;

    @ColumnInfo(name = "first")
    public String first ;


}
