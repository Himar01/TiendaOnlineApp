package com.example.tiendaonlineapp.data;

import static androidx.room.ForeignKey.CASCADE;

import android.text.SpannableString;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "products",
        foreignKeys = @ForeignKey(
                entity = CategoryItem.class,
                parentColumns = "id",
                childColumns = "category_id",
                onDelete = CASCADE
        )
)
public class ProductItem {

    @PrimaryKey
    public int id;

    public String product;
    public String details;
    public String picture;
    public float price;

    @ColumnInfo(name = "category_id")
    public int categoryId;


    @Override
    public String toString() {
        return product;
    }
}
