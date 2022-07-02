package com.example.tiendaonlineapp.categories;


import android.util.Log;

import com.example.tiendaonlineapp.data.ProductItem;
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
        repository.getProduct(0,new RepositoryContract.GetProductCallback(){

            @Override
            public void setProduct(ProductItem product) {
                if (product == null) {
                    repository.loadCatalog(
                            true, new RepositoryContract.FetchCatalogDataCallback() {

                                @Override
                                public void onCatalogDataFetched(boolean error) {
                                    if (!error) {
                                        repository.getCategoryList(callback);
                                    }
                                }
                            });

                }else{
                    repository.getCategoryList(callback);
                }
            }
        });


    }

}