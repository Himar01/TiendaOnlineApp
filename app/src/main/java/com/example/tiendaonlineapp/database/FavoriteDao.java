package com.example.tiendaonlineapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tiendaonlineapp.data.FavoriteItem;

import java.util.List;

@Dao
public interface FavoriteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteItem(FavoriteItem favItem);

    @Query("SELECT * FROM favorites WHERE username = :username")
    List<FavoriteItem> loadFavoriteItems(String username);

    @Delete
    void deleteFavoriteItem(FavoriteItem favItem);
}
