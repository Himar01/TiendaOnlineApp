package com.example.tiendaonlineapp.data;


import java.util.List;

public interface RepositoryContract {


    //INTERFACES-----------------------------

    interface FetchCatalogDataCallback {
        void onCatalogDataFetched(boolean error);
    }

    interface GetProductListCallback {
        void setProductList(List<ProductItem> products);
    }
    interface GetProductCallback {
        void setProduct(ProductItem product);
//        void setProduct(ProductItem product);
    }

    interface GetCategoryListCallback {
        void setCategoryList(List<CategoryItem> categories);
    }

    interface GetCategoryCallback {
        void setCategory(CategoryItem category);
    }

    interface DeleteCategoryCallback {
        void onCategoryDeleted();
    }

    interface UpdateCategoryCallback {
        void onCategoryUpdated();
    }

    interface DeleteProductCallback {
        void onProductDeleted();
    }

    interface UpdateProductCallback {
        void onProductUpdated();
    }
    interface CheckUsernameCallback{
        void onUsernameChecked(boolean isValid);
    }
    interface InsertUsernameCallback{
        void onUsernameInserted();
    }
    interface LoginUsername{
        void onUsernameChecked(String token);
    }
    interface InsertFavoriteItemCallback{
        void FavoriteItemInserted();
    }
    interface DeleteFavoriteItemCallback{
        void FavoriteItemDeleted();
    }
    interface CheckFavoriteItemCallback{
        void FavoriteItemChecked(boolean exists);
    }
    interface GetUserProductsCallback{
        void setUserProducts(List<ProductItem> products);
    }
    //METHODS-----------------------------

    void loadCatalog(
            boolean clearFirst, OnlineShopRepository.FetchCatalogDataCallback callback);

    void getProductList(
            CategoryItem category, OnlineShopRepository.GetProductListCallback callback);

    void getProductList(
            int categoryId, OnlineShopRepository.GetProductListCallback callback);

    void getProduct(int id, OnlineShopRepository.GetProductCallback callback);
    void getCategory(int id, OnlineShopRepository.GetCategoryCallback callback);
    void getCategoryList(OnlineShopRepository.GetCategoryListCallback callback);


    void deleteProduct(
            ProductItem product, OnlineShopRepository.DeleteProductCallback callback);

    void updateProduct(
            ProductItem product, OnlineShopRepository.UpdateProductCallback callback);

    void deleteCategory(
            CategoryItem category, OnlineShopRepository.DeleteCategoryCallback callback);

    void updateCategory(
            CategoryItem category, OnlineShopRepository.UpdateCategoryCallback callback);
    void checkUsernameValid(String username,
                            RepositoryContract.CheckUsernameCallback callback);
    void insertUsername(User user,
                            RepositoryContract.InsertUsernameCallback callback);
    void loginUser(User user,
                            RepositoryContract.LoginUsername callback);
    void insertFavoriteItem(FavoriteItem item, String token,
                            RepositoryContract.InsertFavoriteItemCallback callback);
    void deleteFavoriteItem(FavoriteItem item, String token,
                            RepositoryContract.DeleteFavoriteItemCallback callback);
    void checkFavoriteItem(FavoriteItem item, String token,
                            RepositoryContract.CheckFavoriteItemCallback callback);
    void getUserProducts(String username, int categoryId, String token,
                            RepositoryContract.GetUserProductsCallback callback);
    void getCategoryList(String username, String token,
                            RepositoryContract.GetCategoryListCallback callback);
}
