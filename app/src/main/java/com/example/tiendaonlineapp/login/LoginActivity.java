package com.example.tiendaonlineapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
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
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }
    @Override
    public void showToastAnimation(int message, boolean isGood){
        TextView toast = findViewById(R.id.toast);
        if (isGood) {
            toast.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_good));
        } else {
            toast.setBackground(AppCompatResources.getDrawable(this, R.drawable.border_wrong));
        }
        toast.setText(getString(message));
        Animation out = new AlphaAnimation(1.0f,0.0f);
        out.setDuration(1000);
        Animation in = new AlphaAnimation(0.0f,1.0f);
        in.setDuration(1000);
        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG,"Out Animation Ended");
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
                Log.d(TAG,"In Animation Ended");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.startAnimation(out);
                    }
                }, 2000);

            }
        });
        toast.setVisibility(View.VISIBLE);
        toast.startAnimation(in);
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