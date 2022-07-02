package com.example.tiendaonlineapp.data;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "favorites",
        primaryKeys = { "productId", "username" },
        foreignKeys = {@ForeignKey(
                entity = ProductItem.class,
                parentColumns = "id",
                childColumns = "productId",
                onDelete = CASCADE
        ), @ForeignKey(
                entity = User.class,
                parentColumns = "username",
                childColumns = "username",
                onDelete = CASCADE
        )}
)
public class FavoriteItem {
    @NonNull
    public int productId;
    @NonNull
    public String username;

}
