package com.example.tiendaonlineapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
        setUpButtons();
        // do the setup
        RegisterScreen.configure(this);


    }



    private void setUpButtons() {
        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(view -> {
            loginButtonPressed();
        });
    }


    private void loginButtonPressed() {
        presenter.loginButtonPressed();
    }




    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void finishActivity() {
        finish();
    }
}