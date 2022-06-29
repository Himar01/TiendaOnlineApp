package com.example.tiendaonlineapp.data;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "favoriteItem",
        primaryKeys = { "productId", "username" },
        foreignKeys = {@ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "productId",
                onDelete = CASCADE
        ), @ForeignKey(
                entity = ProductItem.class,
                parentColumns = "username",
                childColumns = "username",
                onDelete = CASCADE
        )}
)
public class FavoriteItem {
    public int productId;
    public String username;

}
