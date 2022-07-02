package com.example.tiendaonlineapp.login;

import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void navigateToNextActivity(Class c);

        void showToastAnimation(int message, boolean isGood);

        void resetScroll();

        void finishActivity();

        void erasePasswords();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void registerButtonPressed();

        void loginButtonPressed(String username, String password);

        void onResume();
    }

    interface Model {
        void loginUser(String username, String password, RepositoryContract.LoginUsername callback);
    }
}