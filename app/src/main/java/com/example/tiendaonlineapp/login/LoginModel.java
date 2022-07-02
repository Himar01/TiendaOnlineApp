package com.example.tiendaonlineapp.login;

import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.data.User;

public class LoginModel implements LoginContract.Model {

    //public static String TAG = LoginModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginModel";

    private RepositoryContract repository;

    public LoginModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void loginUser(String username, String password, RepositoryContract.LoginUsername callback) {
        User user = new User(username,password);
        repository.loginUser(user,callback);
    }
}