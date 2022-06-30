package com.example.tiendaonlineapp.login;
public class LoginModel implements LoginContract.Model {

    //public static String TAG = LoginModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginModel";

    private String data;

    public LoginModel(String data) {
        this.data = data;
    }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");

        return data;
    }

    @Override
    public String getUpdatedDataDuringPause() {
        // Log.e(TAG, "getUpdatedDataDuringPause()");

        return data;
    }

    @Override
    public void onUpdatedDataFromRestartedScreen(String data) {
        // Log.e(TAG, "onUpdatedDataFromRestartedScreen()");


    }

    @Override
    public void onUpdatedDataFromNextScreen(String data) {
        // Log.e(TAG, "onUpdatedDataFromNextScreen()");


    }

    @Override
    public void onUpdatedDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onUpdatedDataFromPreviousScreen()");


    }
}