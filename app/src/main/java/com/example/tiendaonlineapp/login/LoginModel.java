package com.example.tiendaonlineapp.login;

import com.example.tiendaonlineapp.data.RepositoryContract;

public class LoginModel implements LoginContract.Model {

    //public static String TAG = LoginModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginModel";

    private RepositoryContract repository;

    public LoginModel(RepositoryContract repository) {
        this.repository = repository;
    }

}