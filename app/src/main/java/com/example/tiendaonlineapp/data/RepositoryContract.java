package com.example.tiendaonlineapp.data;


import java.util.List;

public interface RepositoryContract {

    interface FetchCatalogDataCallback {
        void onCatalogDataFetched(boolean error);
    }

    interface GetProductListCallback {
        void setProductList(List<ProductItem> products);
    }

    interface GetProductCallback {
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
}
