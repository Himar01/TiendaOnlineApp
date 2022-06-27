package com.example.tiendaonlineapp.app;

import com.example.tiendaonlineapp.categories.CategoryListState;
import com.example.tiendaonlineapp.product.ProductDetailState;
import com.example.tiendaonlineapp.products.ProductListState;

public class AppMediator {

    private static AppMediator INSTANCE;

    private CategoryListState categoryListState = new CategoryListState();
    private ProductListState productListState = new ProductListState();
    private ProductDetailState productDetailState = new ProductDetailState();

    private AppMediator() {

    }


    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }
    public static void resetInstance() {
        INSTANCE = null;
    }
    public void setProductListState(ProductListState state) {
        productListState = state;
    }
    public void setProductDetailState(ProductDetailState state){productDetailState = state;}
    public CategoryListState getCategoryListState() {
        return categoryListState;
    }

    public ProductListState getProductListState() {return productListState;
    }

    public ProductDetailState getProductDetailState(){
        return productDetailState;
    }
}
