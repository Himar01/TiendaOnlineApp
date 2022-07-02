package com.example.tiendaonlineapp.login;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void navigateToNextActivity(Class c);
        void showToastAnimation(int message, boolean isGood);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void registerButtonPressed();
        void onResume();
    }

    interface Model {

    }

}