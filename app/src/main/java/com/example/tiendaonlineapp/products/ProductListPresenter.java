package com.example.tiendaonlineapp.products;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.categories.CategoryListState;
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

    private void fetchUserProductListData(){
        model.fetchUserProductListData(state.categoryId, mediator.user.username,
                mediator.user.token, new RepositoryContract.GetUserProductsCallback(){
                    @Override
                    public void setUserProducts(List<ProductItem> products) {
                        state.likedProducts = products;
                        view.get().displayData(state,true);
                    }
                });

    }

    private void displayData(){
        if(mediator.user!=null) {
            view.get().userLogged(mediator.user.username);
            if (state.likeButtonChecked) {
                fetchUserProductListData();
            } else {
                view.get().displayData(state, false);
            }
        }else{
            view.get().userLogout();
            view.get().displayData(state, false);
        }
    }
    @Override
    public void onBackPressed() {
        CategoryListState state = new CategoryListState();
        state.likeButtonChecked = this.state.likeButtonChecked;
        mediator.setCategoryListState(state);
    }


    @Override
    public void fetchCategoryListData() {
            model.fetchProductListData(state.categoryId, new RepositoryContract.GetProductListCallback() {
                @Override
                public void setProductList(List<ProductItem> products) {
                    state.currentProducts = products;
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
        displayData();
    }

    @Override
    public void likeButtonPressed() {
        state.likeButtonChecked = view.get().likeButtonIsChecked();
        displayData();
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