package com.example.tiendaonlineapp.register;

import com.example.tiendaonlineapp.data.RepositoryContract;

public class RegisterModel implements RegisterContract.Model {

    //public static String TAG = LoginModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginModel";

    private RepositoryContract repository;

    public RegisterModel(RepositoryContract repository) {
        this.repository = repository;
    }


}