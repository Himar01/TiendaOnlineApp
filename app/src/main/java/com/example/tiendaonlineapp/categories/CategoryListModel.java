package com.example.tiendaonlineapp.categories;


import android.util.Log;

import com.example.tiendaonlineapp.data.RepositoryContract;

public class CategoryListModel implements CategoryListContract.Model {

    //public static String TAG = CategoryListModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.CategoryListModel";

    private RepositoryContract repository;

    public CategoryListModel(RepositoryContract repository) {
        this.repository = repository;

    }

    @Override
    public void fetchCategoryListData(
            final RepositoryContract.GetCategoryListCallback callback) {

        Log.e(TAG, "fetchCategoryListData()");

        repository.loadCatalog(
                true, new RepositoryContract.FetchCatalogDataCallback() {

                    @Override
                    public void onCatalogDataFetched(boolean error) {
                        if(!error) {
                            repository.getCategoryList(callback);
                        }
                    }
                });

    }

}