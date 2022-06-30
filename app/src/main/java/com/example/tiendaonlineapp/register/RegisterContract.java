package com.example.tiendaonlineapp.register;

import java.lang.ref.WeakReference;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);


    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

    }

    interface Model {

    }

}