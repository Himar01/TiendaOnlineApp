package com.example.tiendaonlineapp.product;


import com.example.tiendaonlineapp.data.RepositoryContract;

public class ProductDetailModel implements ProductDetailContract.Model {

    //public static String TAG = ProductDetailModel.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.ProductDetailModel";

    private RepositoryContract repository;

    public ProductDetailModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchProductListData(int productId, RepositoryContract.GetProductCallback callback){
        repository.getProduct(productId, callback);
    }

}