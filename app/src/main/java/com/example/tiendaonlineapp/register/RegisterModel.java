package com.example.tiendaonlineapp.register;

import android.util.Log;

import com.example.tiendaonlineapp.data.RepositoryContract;

public class RegisterModel implements RegisterContract.Model {

    //public static String TAG = LoginModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginModel";

    private RepositoryContract repository;

    public RegisterModel(RepositoryContract repository) {
        this.repository = repository;
    }
    @Override
    public void checkUsernameValid(String username,
                                     final RepositoryContract.CheckUsernameCallback callback) {
        Log.d(TAG, "checkUsernameValid()");
        repository.checkUsernameValid(username, callback);

    }

}