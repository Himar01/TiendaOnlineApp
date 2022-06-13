package com.example.tiendaonlineapp.products;


import android.util.Log;

import com.example.tiendaonlineapp.data.RepositoryContract;

public class ProductListModel implements ProductListContract.Model {

    //public static String TAG = ProductListModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.ProductListModel";

    private RepositoryContract repository;

    public ProductListModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void fetchProductListData(int categoryId,
                                      final RepositoryContract.GetProductListCallback callback) {

            Log.e(TAG, "fetchCategoryListData()");

            repository.getProductList(categoryId, callback);

        }

}