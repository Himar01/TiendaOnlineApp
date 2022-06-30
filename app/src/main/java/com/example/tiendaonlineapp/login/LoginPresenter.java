package com.example.tiendaonlineapp.login;

import com.example.tiendaonlineapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class
LoginPresenter implements LoginContract.Presenter {

    //public static String TAG = LoginPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginPresenter";

    private WeakReference<LoginContract.View> view;
    private LoginState state;
    private LoginContract.Model model;
    private AppMediator mediator;

    public LoginPresenter(AppMediator mediator) {
        this.mediator = mediator;
        //state = mediator.getLoginState();
    }




    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

}