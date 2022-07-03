package com.example.tiendaonlineapp.product;


import com.example.tiendaonlineapp.data.FavoriteItem;
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

    @Override
    public void insertFavItem(int productId, String username, String token, RepositoryContract.InsertFavoriteItemCallback callback) {
        FavoriteItem item = new FavoriteItem();
        item.productId=productId;
        item.username=username;
        repository.insertFavoriteItem(item, token, callback);
    }

    @Override
    public void deleteFavItem(int productId, String username, String token, RepositoryContract.DeleteFavoriteItemCallback callback) {
        FavoriteItem item = new FavoriteItem();
        item.productId=productId;
        item.username=username;
        repository.deleteFavoriteItem(item, token, callback);
    }

    @Override
    public void checkFavItemExists(int productId, String username, String token, RepositoryContract.CheckFavoriteItemCallback callback) {
        FavoriteItem item = new FavoriteItem();
        item.productId=productId;
        item.username=username;
        repository.checkFavoriteItem(item, token, callback);
    }



}