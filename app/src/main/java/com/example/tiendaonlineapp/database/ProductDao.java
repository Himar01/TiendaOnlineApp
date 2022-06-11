package com.example.tiendaonlineapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tiendaonlineapp.data.ProductItem;

import java.util.List;

@Dao
public interface ProductDao {


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertProduct(ProductItem product);

  @Update
  void updateProduct(ProductItem product);

  @Delete
  void deleteProduct(ProductItem product);

  @Query("SELECT * FROM products")
  List<ProductItem> loadProducts();

  @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
  ProductItem loadProduct(int id);

  @Query("SELECT * FROM products WHERE category_id=:categoryId")
  List<ProductItem> loadProducts(final int categoryId);
}
