package com.example.tiendaonlineapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity(tableName = "users")
public class User {


    @PrimaryKey
    @NonNull
    public String username;
    public String hashedPassword;
    public String token;
    public User(){

    }
    public User(String username, String password){
        this.username = username;
        this.hashedPassword = md5Hash(password);
    }
    public static String md5Hash(String message) {
        String md5 = "";
        if(null == message)
            return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");//Create MessageDigest object for MD5
            digest.update(message.getBytes(), 0, message.length());//Update input string in message digest
            md5 = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
    @Override
    public String toString() {
        return username;
    }
}
