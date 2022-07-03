package com.example.tiendaonlineapp.product;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.login.LoginActivity;

public class ProductDetailActivity
        extends AppCompatActivity implements ProductDetailContract.View {


    public static String TAG = "TiendaOnlineApp.ProductDetailActivity";

    private ProductDetailContract.Presenter presenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
        setUpButtons();
        // Log.e(TAG, "onCreate()");

        // do the setup
        ProductDetailScreen.configure(this);
        presenter.fetchProductDetail();
    }
    private void setUpButtons() {
        TextView login = findViewById(R.id.login);
        login.setOnClickListener(view -> loginButtonPressed());
        ImageButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(view -> logoutButtonPressed());
    }

    private void logoutButtonPressed() {
        presenter.logoutButtonPressed();
    }

    private void loginButtonPressed() {
        presenter.loginButtonPressed();
    }

    @Override
    public void userLogged(String username) {
        TextView login,greeting;
        ImageButton logout;

        login=findViewById(R.id.login);
        greeting=findViewById(R.id.greeting);
        login.setVisibility(View.GONE);
        greeting.setVisibility(View.VISIBLE);
        username = username.substring(0,1).toUpperCase()+username.substring(1);
        greeting.setText(getString(R.string.greeting,username));

        logout=findViewById(R.id.logout);
        logout.setVisibility(View.VISIBLE);
    }

    @Override
    public void userLogout() {
        TextView login,greeting;
        ImageButton logout;

        login=findViewById(R.id.login);
        greeting=findViewById(R.id.greeting);
        login.setVisibility(View.VISIBLE);
        greeting.setVisibility(View.GONE);

        logout=findViewById(R.id.logout);
        logout.setVisibility(View.GONE);

    }

    @Override
    public void navigateToNextActivity(Class<LoginActivity> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    @Override
    public void displayData(ProductDetailViewModel viewModel) {
        //Log.e(TAG, "onViewModelDataUpdated()");

        // deal with the data
        ((TextView) findViewById(R.id.productName)).setText(viewModel.currentProduct.product);
        String priceTotal = valueOf(viewModel.currentProduct.price);
        String[] price = priceTotal.split("\\.");
        if(price[1].equals("0")){
            price[1]="00";
        }
        ((TextView) findViewById(R.id.productPrice)).setText(price[0]+","+price[1]+"€");
        String details = viewModel.currentProduct.details;
        SpannableString indentedDetails = createIndentedText(details,0,100);
        ((TextView) findViewById(R.id.productDetails)).setText(indentedDetails);
        ((ImageView) findViewById(R.id.productImage)).setImageResource(getResourceId(viewModel.currentProduct.picture,
                "drawable",
                getPackageName()));;
        ToggleButton like = findViewById(R.id.likeButton);
        like.setOnClickListener(view -> toggleButtonPressed(like.isChecked()));
    }

    private void toggleButtonPressed(boolean isChecked) {
        presenter.toggleButtonPressed(isChecked);
    }

    private int getResourceId(String pVariableName, String pResourcename, String pPackageName)
    {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private SpannableString createIndentedText(String text, int marginFirstLine, int marginNextLines) {
        text = text.replace("\t", "   ");
        SpannableString result = new SpannableString(text);
        result.setSpan(new LeadingMarginSpan.Standard(marginFirstLine, marginNextLines),0,text.length(),0);
        return result;
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
    public void changeToggleButtonState() {
        runOnUiThread (new Thread(new Runnable() {
            public void run() {
        ToggleButton like = findViewById(R.id.likeButton);
        like.setChecked(!like.isChecked());
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void injectPresenter(ProductDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}