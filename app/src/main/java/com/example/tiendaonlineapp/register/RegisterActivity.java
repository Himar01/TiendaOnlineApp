package com.example.tiendaonlineapp.register;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import com.example.tiendaonlineapp.R;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    //public static String TAG = LoginActivity.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.LoginActivity";

    private RegisterContract.Presenter presenter;
    private EditText username,password,passwordRepetition;

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
        setUpViews();
        // do the setup
        RegisterScreen.configure(this);


    }



    private void setUpViews() {
        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(view -> {
            loginButtonPressed();
        });
        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(view -> {
            registerButtonPressed();
        });
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordRepetition = findViewById(R.id.password_repetition);
/*        passwordRepetition.setKeyListener(new KeyListener() {
            @Override
            public int getInputType() {
                Log.d(TAG, "Tecla pulsada");
                return 0;
            }

            @Override
            public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
                return false;
            }

            @Override
            public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
                return false;
            }

            @Override
            public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
                return false;
            }

            @Override
            public void clearMetaKeyState(View view, Editable editable, int i) {

            }
        });*/
    }


    @Override
    public void showToastAnimation(int message, boolean isGood){
        runOnUiThread (new Thread(new Runnable() {
            public void run() {
                TextView toast = findViewById(R.id.toast);
                if (isGood) {
                    toast.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.border_good));
                } else {
                    toast.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.border_wrong));
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
        }));
    }

    @Override
    public void erasePasswords() {
        password.setText("");
        passwordRepetition.setText("");
    }

    private void loginButtonPressed() {
        presenter.loginButtonPressed();
    }
    private void registerButtonPressed() {
        presenter.registerButtonPressed(username.getText().toString(), new String[]{
                password.getText().toString(),
                passwordRepetition.getText().toString()});
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