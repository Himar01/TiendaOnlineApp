package com.example.tiendaonlineapp.categories;

import android.util.Log;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
import com.example.tiendaonlineapp.data.CategoryItem;
import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.login.LoginActivity;
import com.example.tiendaonlineapp.products.ProductListActivity;
import com.example.tiendaonlineapp.products.ProductListState;

import java.lang.ref.WeakReference;
import java.util.List;

public class CategoryListPresenter implements CategoryListContract.Presenter {

    //public static String TAG = CategoryListPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.CategoryListPresenter";

    private WeakReference<CategoryListContract.View> view;
    private CategoryListState state;
    private CategoryListContract.Model model;
    private AppMediator mediator;
    private UserLog userLog;
    public CategoryListPresenter(AppMediator mediator,UserLog userLog) {
        this.userLog = userLog;
        this.mediator = mediator;
        state = mediator.getCategoryListState();
    }

    private void fetchUserProductListData(){
        model.fetchUserProductListData(userLog.user.username,
                userLog.user.token, new RepositoryContract.GetCategoryListCallback(){
                    @Override
                    public void setCategoryList(List<CategoryItem> categories) {
                        state.likedCategories = categories;
                        view.get().displayData(state,true);
                    }
                });

    }

    private void displayData(){
        if(userLog.user!=null) {
            view.get().userLogged(userLog.user.username);
            if (state.likeButtonChecked) {
                fetchUserProductListData();
            } else {
                fetchCategoryListData();
            }
        }else{
            view.get().userLogout();
            fetchCategoryListData();
        }
    }

    private void fetchCategoryListData() {
        // Log.e(TAG, "fetchCategoryListData()");
        // call the model
        model.fetchCategoryListData(new RepositoryContract.GetCategoryListCallback() {
            @Override
            public void setCategoryList(List<CategoryItem> categories) {
                state.categories = categories;
                view.get().displayData(state, false);
            }
        });
    }

    @Override
    public void onResume() {
        CategoryListState savedState = mediator.getCategoryListState();
        if(savedState!=null){
            state = savedState;
        }
        displayData();
    }

    @Override
    public void logoutButtonPressed() {
        userLog.user=null;
        view.get().userLogout();
    }

    @Override
    public void likeButtonPressed() {
        state.likeButtonChecked = view.get().likeButtonIsChecked();
        Log.e(TAG,"likeButtonChecked:"+ state.likeButtonChecked);
        displayData();
    }

    @Override
    public void injectView(WeakReference<CategoryListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CategoryListContract.Model model) {
        this.model = model;
    }

    @Override
    public void onCreate() {
        model.clearAllTables();
    }

    @Override
    public void selectCategoryListData(CategoryItem item) {
        ProductListState pdState = new ProductListState();
        pdState.categoryId = item.id;
        pdState.likeButtonChecked = state.likeButtonChecked;
        mediator.setProductListState(pdState);
        view.get().navigateToNextActivity(ProductListActivity.class);
    }

    @Override
    public void loginButtonPressed() {
            view.get().navigateToNextActivity(LoginActivity.class);
        }
 }


