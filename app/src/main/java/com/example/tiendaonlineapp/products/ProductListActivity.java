package com.example.tiendaonlineapp.products;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        presenter.onCreate();
        int[] imgResources = presenter.loadImgResources();

        listAdapter = new ProductListAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProductItem item = (ProductItem) view.getTag();
                presenter.selectCategoryListData(item);
            }
        },imgResources);
        RecyclerView recyclerView = findViewById(R.id.product_list);
        recyclerView.setAdapter(listAdapter);


        presenter.fetchCategoryListData();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Log.e(TAG, "onBackPressed()");

        presenter.onBackPressed();
    }



    @Override
    public void displayData(ProductListViewModel viewModel) {
        Log.e(TAG, "displayCategoryListData()");

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // deal with the data
                listAdapter.setItems(viewModel.products);
            }

        });
    }


    @Override
    public void navigateToNextScreen() {
        // Log.e(TAG, "navigateToNextScreen()");

        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToPreviousScreen() {
        // Log.e(TAG, "navigateToPreviousScreen()");

        finish();
    }

    @Override
    public void injectPresenter(ProductListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}