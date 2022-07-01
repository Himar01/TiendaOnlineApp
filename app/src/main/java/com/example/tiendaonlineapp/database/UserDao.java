package com.example.tiendaonlineapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tiendaonlineapp.data.User;

@Dao
public interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT username FROM users WHERE username = :username AND hashedPassword = :hashedPassword LIMIT 1")
    String checkUsernameValid(String username, String hashedPassword);

    @Query("SELECT username FROM users WHERE username = :username LIMIT 1")
    String checkUsernameExists(String username);
}
