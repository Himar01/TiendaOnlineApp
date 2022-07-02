package com.example.tiendaonlineapp.product;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.ProductItem;
import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.login.LoginActivity;

import java.lang.ref.WeakReference;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    //public static String TAG = ProductDetailPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.ProductDetailPresenter";

    private WeakReference<ProductDetailContract.View> view;
    private ProductDetailState state;
    private ProductDetailContract.Model model;
    private AppMediator mediator;

    public ProductDetailPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProductDetailState();

    }
    @Override
    public void fetchProductDetail(){
        model.fetchProductListData(state.productId, new RepositoryContract.GetProductCallback() {
            @Override
            public void setProduct(ProductItem product){
                state.currentProduct = product;
                view.get().displayData(state);
            }
        });
    }


    @Override
    public void onResume() {
        ProductDetailState savedState = mediator.getProductDetailState();
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
    public void logoutButtonPressed() {
        mediator.user = null;
        view.get().userLogout();
    }

    @Override
    public void loginButtonPressed() {
        view.get().navigateToNextActivity(LoginActivity.class);
    }

    @Override
    public void injectView(WeakReference<ProductDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductDetailContract.Model model) {
        this.model = model;
    }

}