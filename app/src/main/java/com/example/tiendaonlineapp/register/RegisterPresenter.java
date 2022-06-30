package com.example.tiendaonlineapp.register;

import com.example.tiendaonlineapp.app.AppMediator;

import java.lang.ref.WeakReference;

public class
RegisterPresenter implements RegisterContract.Presenter {

    //public static String TAG = LoginPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginPresenter";

    private WeakReference<RegisterContract.View> view;
    private RegisterState state;
    private RegisterContract.Model model;
    private AppMediator mediator;

    public RegisterPresenter(AppMediator mediator) {
        this.mediator = mediator;
        //state = mediator.getLoginState();
    }




    @Override
    public void injectView(WeakReference<RegisterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RegisterContract.Model model) {
        this.model = model;
    }

    @Override
    public void loginButtonPressed() {
        view.get().finishActivity();
    }

}