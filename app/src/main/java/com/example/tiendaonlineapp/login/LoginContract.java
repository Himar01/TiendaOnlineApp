package com.example.tiendaonlineapp.login;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void navigateToNextActivity(Class c);

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void registerButtonPressed();
    }

    interface Model {

    }

}