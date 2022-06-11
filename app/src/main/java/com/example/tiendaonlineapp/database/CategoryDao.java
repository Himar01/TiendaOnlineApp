package com.example.tiendaonlineapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tiendaonlineapp.data.CategoryItem;

import java.util.List;

@Dao
public interface CategoryDao {


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertCategory(CategoryItem category);

  @Update
  void updateCategory(CategoryItem category);

  @Delete
  void deleteCategory(CategoryItem category);

  @Query("SELECT * FROM categories")
  List<CategoryItem> loadCategories();

  @Query("SELECT * FROM categories WHERE id = :id LIMIT 1")
  CategoryItem loadCategory(int id);
}
