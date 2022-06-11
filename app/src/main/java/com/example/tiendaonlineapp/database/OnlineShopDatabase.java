package com.example.tiendaonlineapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tiendaonlineapp.data.CategoryItem;
import com.example.tiendaonlineapp.data.ProductItem;
@Database(entities = {CategoryItem.class, ProductItem.class}, version = 1)
public abstract class OnlineShopDatabase  extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract ProductDao productDao();
}
