package com.example.tiendaonlineapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tiendaonlineapp.data.CategoryItem;
import com.example.tiendaonlineapp.data.FavoriteItem;
import com.example.tiendaonlineapp.data.ProductItem;
import com.example.tiendaonlineapp.data.User;

@Database(entities = {CategoryItem.class, ProductItem.class, User.class, FavoriteItem.class}, version = 1)
public abstract class OnlineShopDatabase  extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract ProductDao productDao();
    public abstract UserDao userDao();
    public abstract FavoriteDao favoriteDao();
}
