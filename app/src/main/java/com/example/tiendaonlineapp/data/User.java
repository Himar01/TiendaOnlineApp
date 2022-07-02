package com.example.tiendaonlineapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    public String username;
    public String hashedPassword;


    @Override
    public String toString() {
        return username;
    }
}
