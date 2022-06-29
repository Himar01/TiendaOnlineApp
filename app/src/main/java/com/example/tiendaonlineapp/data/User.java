package com.example.tiendaonlineapp.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public String username;

    public String hashedPassword;
    public String svg;
    public String symbol;
    @Ignore
    @SerializedName("products")
    public List<ProductItem> items;

    @Override
    public String toString() {
        return username;
    }
}
