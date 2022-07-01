package com.example.tiendaonlineapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    public String username;
    public String hashedPassword;


    @Override
    public String toString() {
        return username;
    }
}
