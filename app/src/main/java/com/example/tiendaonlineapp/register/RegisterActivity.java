package com.example.tiendaonlineapp.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
        startToastAnimation("Las contraseñas no coinciden");


    }
    private void startToastAnimation(String mensaje){
        TextView toast = findViewById(R.id.toast);
        toast.setVisibility(View.VISIBLE);
        Animation out = new AlphaAnimation(1.0f,0.0f);
        out.setDuration(3000);
        Animation in = new AlphaAnimation(0.0f,1.0f);
        out.setDuration(3000);
        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                toast.setVisibility(View.GONE);

            }
        });
        in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                toast.startAnimation(in);

            }
        });
        toast.startAnimation(in);
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