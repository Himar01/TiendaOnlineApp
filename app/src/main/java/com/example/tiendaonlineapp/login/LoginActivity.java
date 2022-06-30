package com.example.tiendaonlineapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tiendaonlineapp.R;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    //public static String TAG = LoginActivity.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginActivity";

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }

        setUpButtons();
        // Log.e(TAG, "onCreate()");

        // do the setup
        LoginScreen.configure(this);
    }

    private void setUpButtons() {
        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(view -> {
            registerButtonPressed();
        });
    }

    private void registerButtonPressed() {
        presenter.registerButtonPressed();
    }

    @Override
    public void navigateToNextActivity(Class c) {
        // Log.e(TAG, "navigateToNextScreen()");
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}