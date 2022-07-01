package com.example.tiendaonlineapp.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tiendaonlineapp.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    //public static String TAG = LoginActivity.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginActivity";

    private RegisterContract.Presenter presenter;
    private View toastLayout;

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
        LayoutInflater inflater = getLayoutInflater();
        toastLayout = inflater.inflate(R.layout.toast,
                null);

        // do the setup
        RegisterScreen.configure(this);


    }



    private void setUpButtons() {
        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(view -> {
            loginButtonPressed();
        });
        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(view -> {
            registerButtonPressed();
        });
    }

    private void registerButtonPressed() {

    TextView toast = findViewById(R.id.toast);
    toast.setText("Contraseña incorrecta");
    toast.setVisibility(View.VISIBLE);
        toast.animate()
                .alpha(0.0f)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        toast.clearAnimation();
                        toast.setVisibility(View.GONE);
                    }
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