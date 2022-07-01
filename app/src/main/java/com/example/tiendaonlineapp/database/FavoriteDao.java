package com.example.tiendaonlineapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tiendaonlineapp.data.CategoryItem;
import com.example.tiendaonlineapp.data.FavoriteItem;

@Dao
public interface FavoriteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteItem(FavoriteItem favItem);

    @Delete
    void deleteFavoriteItem(FavoriteItem favItem);

    @Query("SELECT productId FROM favorites WHERE username = :username")
    CategoryItem loadFavoriteItems(String username);
}
