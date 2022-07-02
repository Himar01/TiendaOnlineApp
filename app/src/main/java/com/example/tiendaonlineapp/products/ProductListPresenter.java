package com.example.tiendaonlineapp.products;

import android.util.Log;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.ProductItem;
import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.login.LoginActivity;
import com.example.tiendaonlineapp.product.ProductDetailActivity;
import com.example.tiendaonlineapp.product.ProductDetailState;

import java.lang.ref.WeakReference;
import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter {

    //public static String TAG = ProductListPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.ProductListPresenter";

    private WeakReference<ProductListContract.View> view;
    private ProductListState state;
    private ProductListContract.Model model;
    private AppMediator mediator;

    public ProductListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProductListState();
    }


    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void fetchCategoryListData() {
        // Log.e(TAG, "fetchCategoryListData()");

        // call the model
        model.fetchProductListData(state.categoryId,new RepositoryContract.GetProductListCallback() {
            @Override
            public void setProductList(List<ProductItem> products) {
                state.currentProducts = products;
                view.get().displayData(state);
            }
        });
    }

    @Override
    public void selectCategoryListData(ProductItem item) {
        ProductDetailState productState = new ProductDetailState();
        productState.productId = item.id;
        mediator.setProductDetailState(productState);
        view.get().navigateToNextActivity(ProductDetailActivity.class);
    }


    @Override
    public void onResume() {
        ProductListState savedState = mediator.getProductListState();
        if(savedState!=null){
            state = savedState;
        }
        if(mediator.user!=null){
            view.get().userLogged(mediator.user.username);
        }else{
            view.get().userLogout();
        }
    }

    @Override
    public void likeButtonPressed() {

    }

    @Override
    public void logoutButtonPressed() {
        mediator.user=null;
        view.get().userLogout();
    }

    @Override
    public void loginButtonPressed() {
        view.get().navigateToNextActivity(LoginActivity.class);
    }

    @Override
    public void injectView(WeakReference<ProductListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductListContract.Model model) {
        this.model = model;
    }

}