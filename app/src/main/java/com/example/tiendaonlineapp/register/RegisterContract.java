package com.example.tiendaonlineapp.register;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void finishActivity();
        void showToastAnimation(int message, boolean isGood);

        void erasePasswords();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void loginButtonPressed();

        void registerButtonPressed(String username, String[] passwords);
    }

    interface Model {
        void checkUsernameValid(String username,
                                final RepositoryContract.CheckUsernameCallback callback);
    }

}