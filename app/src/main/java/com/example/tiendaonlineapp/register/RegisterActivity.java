package com.example.tiendaonlineapp.register;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tiendaonlineapp.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    //public static String TAG = LoginActivity.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginActivity";

    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }

        // Log.e(TAG, "onCreate()");

        // do the setup
        RegisterScreen.configure(this);


    }








    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }
}