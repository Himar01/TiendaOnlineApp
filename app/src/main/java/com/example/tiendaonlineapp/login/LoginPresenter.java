package com.example.tiendaonlineapp.login;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.data.User;
import com.example.tiendaonlineapp.register.RegisterActivity;

import java.lang.ref.WeakReference;

public class
LoginPresenter implements LoginContract.Presenter {

    //public static String TAG = LoginPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginPresenter";

    private WeakReference<LoginContract.View> view;
    private LoginState state;
    private LoginContract.Model model;
    private AppMediator mediator;
    private UserLog userLog;

    public LoginPresenter(AppMediator mediator,UserLog userLog) {
        this.mediator = mediator;
        this.userLog = userLog;
        state = mediator.getLoginState();
    }
    @Override
    public void onResume(){
        LoginState savedState = mediator.getLoginState();
        if(savedState!=null){
            state = savedState;
        }
        if(state.toast!=-1){
            view.get().showToastAnimation(state.toast,true);
            state.toast=-1;
        }
        view.get().resetScroll();
    }
    @Override
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

    @Override
    public void registerButtonPressed() {
        view.get().navigateToNextActivity(RegisterActivity.class);
    }

    @Override
    public void loginButtonPressed(String username, String password) {
        model.loginUser(username,password,new RepositoryContract.LoginUsername(){

            @Override
            public void onUsernameChecked(String token) {
             if(token!=null){
                 User user = new User();
                 String lowerUsername = username.toLowerCase();
                 user.username=lowerUsername;
                 user.token=token;
                 userLog.user=user;
                 view.get().finishActivity();
             }else{
                 view.get().showToastAnimation(R.string.loginUnable,false);
                 view.get().erasePasswords();
             }
            }
        });
    }
}