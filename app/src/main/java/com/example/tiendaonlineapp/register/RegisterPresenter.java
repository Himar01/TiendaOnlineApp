package com.example.tiendaonlineapp.register;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.login.LoginState;

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
        state = mediator.getRegisterState();
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

    @Override
    public void registerButtonPressed(String username, String[] passwords) {
        model.checkUsernameValid(username, new RepositoryContract.CheckUsernameCallback(){

            @Override
            public void onUsernameChecked(boolean isValid) {
                if(isValid){
                    LoginState loginState = new LoginState();
                    loginState.toast = R.string.registerConfirmed;
                    mediator.setLoginState(loginState);
                    view.get().finishActivity();
                }else {
                    view.get().showToastAnimation(R.string.userExists, false);
                    view.get().erasePasswords();
                }
            }
        });
    }

}