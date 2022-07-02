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
        state = mediator.getRegisterState();
    }

    @Override
    public void onResume() {
        RegisterState savedState = mediator.getRegisterState();
        if(savedState!=null){
            state = savedState;
        }
        view.get().resetScroll();
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
                    if(username.length()<1){
                        view.get().showToastAnimation(R.string.usernameEmpty, false);
                        view.get().erasePasswords();
                    }else if(!(passwords[0].equals(passwords[1]))){
                        view.get().showToastAnimation(R.string.not_same_password, false);
                        view.get().erasePasswords();
                    }else if(passwords[0].length()<8){
                        view.get().showToastAnimation(R.string.passwordMinChars,false);
                        view.get().erasePasswords();
                    }else{
                        registerUser(username, passwords[0]);
                    }

                }else {
                    view.get().showToastAnimation(R.string.userExists, false);
                    view.get().erasePasswords();
                }
            }
        });
    }

    private void registerUser(String username, String password) {
        model.insertUser(username,password,new RepositoryContract.InsertUsernameCallback(){

            @Override
            public void onUsernameInserted() {

            }
        });
        LoginState loginState = new LoginState();
        loginState.toast = R.string.registerConfirmed;
        mediator.setLoginState(loginState);
        view.get().finishActivity();
    }

}