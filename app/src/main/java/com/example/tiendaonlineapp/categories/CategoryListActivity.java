package com.example.tiendaonlineapp.categories;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.data.CategoryItem;

public class CategoryListActivity
        extends AppCompatActivity implements CategoryListContract.View {


    public static String TAG = "TiendaOnlineApp.CategoryListActivity";

    private CategoryListContract.Presenter presenter;
    private CategoryListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
        listAdapter = new CategoryListAdapter(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CategoryItem item = (CategoryItem) view.getTag();
                presenter.selectCategoryListData(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.category_list);
        recyclerView.setAdapter(listAdapter);
        // Log.e(TAG, "onCreate()");

        // do the setup
        CategoryListScreen.configure(this);

        presenter.fetchCategoryListData();
    }

    @Override
    public void displayCategoryListData(CategoryListViewModel viewModel) {
        Log.e(TAG, "displayCategoryListData()");

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // deal with the data
                listAdapter.setItems(viewModel.categories);
            }

        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void navigateToProductListScreen() {
        // Log.e(TAG, "navigateToNextScreen()");

        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        //Log.e(TAG, "onResume()");
    }

    public void onBackPressed() {
        super.onBackPressed();
        // Log.e(TAG, "onBackPressed()");
    }

    protected void onPause() {
        super.onPause();

        // Log.e(TAG, "onPause()");
    }

    protected void onDestroy() {
        super.onDestroy();
        // Log.e(TAG, "onDestroy()");

    }

    @Override
    public void injectPresenter(CategoryListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}