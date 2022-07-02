package com.example.tiendaonlineapp.register;

import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void finishActivity();

        void showToastAnimation(int message, boolean isGood);

        void erasePasswords();

        void resetScroll();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void loginButtonPressed();

        void registerButtonPressed(String username, String[] passwords);

        void onResume();
    }

    interface Model {
        void checkUsernameValid(String username,
                                final RepositoryContract.CheckUsernameCallback callback);

        void insertUser(String username, String password, RepositoryContract.InsertUsernameCallback callback);
    }

}