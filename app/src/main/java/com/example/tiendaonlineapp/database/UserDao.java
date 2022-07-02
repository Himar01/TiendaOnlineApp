package com.example.tiendaonlineapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tiendaonlineapp.data.User;

@Dao
public interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT username FROM users WHERE username = :username AND hashedPassword = :hashedPassword LIMIT 1")
    String checkUsernameValid(String username, String hashedPassword);

    @Query("SELECT username FROM users WHERE username = :username LIMIT 1")
    String checkUsernameExists(String username);

    @Query("SELECT token FROM users WHERE username=:username AND token=:token LIMIT 1")
    String checkTokenValid(String username, String token);

}
