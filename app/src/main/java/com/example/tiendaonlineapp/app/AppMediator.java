package com.example.tiendaonlineapp.app;

import com.example.tiendaonlineapp.categories.CategoryListState;
import com.example.tiendaonlineapp.product.ProductDetailState;
import com.example.tiendaonlineapp.products.ProductListState;
import com.example.tiendaonlineapp.register.RegisterState;

public class AppMediator {

    private static AppMediator INSTANCE;

    private CategoryListState categoryListState = new CategoryListState();
    private ProductListState productListState = new ProductListState();
    private ProductDetailState productDetailState = new ProductDetailState();
    private RegisterState registerState = new RegisterState();

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

    public CategoryListState getCategoryListState() {
        return categoryListState;
    }

    public void setProductListState(ProductListState state) {
        productListState = state;
    }
    public ProductListState getProductListState() {return productListState;}

    public void setProductDetailState(ProductDetailState state){productDetailState = state;}
    public ProductDetailState getProductDetailState(){
        return productDetailState;
    }
    public RegisterState getRegisterState(){
        return registerState;
    }
}
