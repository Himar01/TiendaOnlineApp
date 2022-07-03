package com.example.tiendaonlineapp.categories;


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

/*        Log.e(TAG, "fetchCategoryListData()");
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
        });*/

        repository.loadCatalog(
                false, new RepositoryContract.FetchCatalogDataCallback() {
                    @Override
                    public void onCatalogDataFetched(boolean error) {
                        if (!error) {
                            repository.getCategoryList(callback);
                        }
                    }
                });
    }

    @Override
    public void fetchUserProductListData(String username, String token, RepositoryContract.GetCategoryListCallback callback) {
        repository.getCategoryList(username,token,callback);
    }

    @Override
    public void clearAllTables() {
        repository.clearAllTables();
    }

}