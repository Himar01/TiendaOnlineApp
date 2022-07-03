package com.example.tiendaonlineapp.products;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.data.ProductItem;

public class ProductListActivity
        extends AppCompatActivity implements ProductListContract.View {


    public static String TAG = "TiendaOnlineApp.ProductListActivity";

    private ProductListContract.Presenter presenter;
    private ProductListAdapter listAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Log.e(TAG, "onCreate()");

        // do the setup
        ProductListScreen.configure(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
        setUpButtons();
        listAdapter = new ProductListAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProductItem item = (ProductItem) view.getTag();
                presenter.selectCategoryListData(item);
            }
        },this);
        RecyclerView recyclerView = findViewById(R.id.product_list);
        recyclerView.setAdapter(listAdapter);
    }

    private void setUpButtons() {
        TextView login = findViewById(R.id.login);
        login.setOnClickListener(view -> loginButtonPressed());
        ImageButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(view -> logoutButtonPressed());
        ToggleButton like = findViewById(R.id.like);
        like.setOnClickListener(view -> likeButtonPressed());
    }

    private void likeButtonPressed() {
        presenter.likeButtonPressed();
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
        ToggleButton like;

        login=findViewById(R.id.login);
        greeting=findViewById(R.id.greeting);
        login.setVisibility(View.GONE);
        greeting.setVisibility(View.VISIBLE);
        username = username.substring(0,1).toUpperCase()+username.substring(1);
        greeting.setText(getString(R.string.greeting,username));

        like=findViewById(R.id.like);
        like.setVisibility(View.VISIBLE);
        logout=findViewById(R.id.logout);
        logout.setVisibility(View.VISIBLE);
    }

    @Override
    public void userLogout() {
        TextView login,greeting;
        ImageButton logout;
        ToggleButton like;

        login=findViewById(R.id.login);
        greeting=findViewById(R.id.greeting);
        login.setVisibility(View.VISIBLE);
        greeting.setVisibility(View.GONE);

        like=findViewById(R.id.like);
        like.setVisibility(View.GONE);
        logout=findViewById(R.id.logout);
        logout.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Log.e(TAG, "onBackPressed()");

        presenter.onBackPressed();
    }

    @Override
    public void displayData(ProductListViewModel viewModel, boolean likedProducts) {
        Log.e(TAG, "displayCategoryListData()");

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // deal with the data
                listAdapter.setItems(likedProducts?viewModel.likedProducts:
                        viewModel.currentProducts);
                ((ToggleButton) findViewById(R.id.like)).setChecked(viewModel.likeButtonChecked);
            }

        });
    }

    @Override
    public boolean likeButtonIsChecked(){
        return ((ToggleButton) findViewById(R.id.like)).isChecked();
    }
    @Override
    public void navigateToNextActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(ProductListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}