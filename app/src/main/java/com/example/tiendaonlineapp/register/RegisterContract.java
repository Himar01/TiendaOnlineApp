package com.example.tiendaonlineapp.register;

import java.lang.ref.WeakReference;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void finishActivity();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void loginButtonPressed();
    }

    interface Model {

    }

}