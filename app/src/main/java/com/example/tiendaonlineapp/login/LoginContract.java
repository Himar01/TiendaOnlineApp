package com.example.tiendaonlineapp.login;

import java.lang.ref.WeakReference;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);


    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

    }

    interface Model {
        String getUpdatedDataDuringPause();

        String getStoredData();

        void onUpdatedDataFromRestartedScreen(String data);

        void onUpdatedDataFromNextScreen(String data);

        void onUpdatedDataFromPreviousScreen(String data);
    }

}