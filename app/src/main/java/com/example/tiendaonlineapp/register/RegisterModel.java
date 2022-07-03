package com.example.tiendaonlineapp.register;

import android.util.Log;

import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.data.User;

public class RegisterModel implements RegisterContract.Model {

    //public static String TAG = LoginModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginModel";

    private RepositoryContract repository;

    public RegisterModel(RepositoryContract repository) {
        this.repository = repository;
    }
    @Override
    public void checkUsernameValid(String username,
                                     RepositoryContract.CheckUsernameCallback callback) {
        Log.d(TAG, "checkUsernameValid()");
        repository.checkUsernameValid(username, callback);
    }

    @Override
    public void insertUser(String username, String password,
                           RepositoryContract.InsertUsernameCallback callback){
        username = username.toLowerCase();
        User newUser = new User(username, password);
        repository.insertUsername(newUser, callback);
    }



}