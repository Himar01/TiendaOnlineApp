package com.example.tiendaonlineapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tiendaonlineapp.data.FavoriteItem;
import com.example.tiendaonlineapp.data.ProductItem;

import java.util.List;

@Dao
public interface FavoriteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteItem(FavoriteItem favItem);

    @Query("SELECT id,product,details,picture,price,category_id FROM users,favorites,products WHERE favorites.username=:username AND users.token=:token AND category_id=:categoryId AND productId=products.id")
    List<ProductItem> loadFavoriteItems(String username, int categoryId, String token);

    @Query("SELECT * FROM favorites,products WHERE username = :username AND productId = :productId LIMIT 1")
    FavoriteItem checkFavoriteItem(String username, int productId);

    @Delete
    void deleteFavoriteItem(FavoriteItem favItem);
}
