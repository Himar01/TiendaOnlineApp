package com.example.tiendaonlineapp.product;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
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
    private UserLog userLog;
    public ProductDetailPresenter(AppMediator mediator,UserLog userLog) {
        this.userLog = userLog;
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
        if(userLog.user!=null){
            model.checkFavItemExists(state.productId, userLog.user.username, userLog.user.token, new RepositoryContract.CheckFavoriteItemCallback() {
                @Override
                public void FavoriteItemChecked(boolean exists) {
                    if(exists) view.get().changeToggleButtonState();
                }
            });
        }
    }


    @Override
    public void onResume() {
        ProductDetailState savedState = mediator.getProductDetailState();
        if(savedState!=null){
            state = savedState;
        }
        if(userLog.user!=null){
            view.get().userLogged(userLog.user.username);
        }else{
            view.get().userLogout();
        }
    }

    @Override
    public void logoutButtonPressed() {
        userLog.user = null;
        view.get().userLogout();
    }

    @Override
    public void loginButtonPressed() {
        view.get().navigateToNextActivity(LoginActivity.class);
    }

    @Override
    public void toggleButtonPressed(boolean isChecked) {
        if(userLog.user==null){
            view.get().showToastAnimation(R.string.mustLoginFirst,false);
            view.get().changeToggleButtonState();
        }else{
            if (isChecked) {
                model.insertFavItem(state.productId, userLog.user.username, userLog.user.token, new RepositoryContract.InsertFavoriteItemCallback(){
                    @Override
                    public void FavoriteItemInserted() {
                        model.checkFavItemExists(state.productId, userLog.user.username, userLog.user.token, new RepositoryContract.CheckFavoriteItemCallback() {
                            @Override
                            public void FavoriteItemChecked(boolean exists) {
                                if(exists){
                                    view.get().showToastAnimation(R.string.FavItemInserted,true);
                                }else{
                                    view.get().showToastAnimation(R.string.FavItemNotInserted,false);
                                }
                            }
                        });
                    }
                });
            } else {
                model.deleteFavItem(state.productId, userLog.user.username, userLog.user.token, new RepositoryContract.DeleteFavoriteItemCallback() {
                    @Override
                    public void FavoriteItemDeleted() {
                        model.checkFavItemExists(state.productId, userLog.user.username, userLog.user.token, new RepositoryContract.CheckFavoriteItemCallback() {
                            @Override
                            public void FavoriteItemChecked(boolean exists) {
                                if(exists) {
                                    view.get().showToastAnimation(R.string.FavItemNotDeleted, false);
                                }else{
                                    view.get().showToastAnimation(R.string.FavItemDeleted, true);
                                }
                            }
                        });
                    }
                });
            }
        }
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