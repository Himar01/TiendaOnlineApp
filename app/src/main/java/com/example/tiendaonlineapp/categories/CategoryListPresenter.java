package com.example.tiendaonlineapp.categories;

import com.example.tiendaonlineapp.app.AppMediator;
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

    public CategoryListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getCategoryListState();
    }

    @Override
    public void onResume() {
        CategoryListState savedState = mediator.getCategoryListState();
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
        mediator.user=null;
        view.get().userLogout();
    }

    @Override
    public void likeButtonPressed() {

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
    public void fetchCategoryListData() {
        // Log.e(TAG, "fetchCategoryListData()");
        // call the model
        model.fetchCategoryListData(new RepositoryContract.GetCategoryListCallback() {

            @Override
            public void setCategoryList(List<CategoryItem> categories) {
                state.categories = categories;
                view.get().displayData(state);
            }
        });
    }

    @Override
    public void selectCategoryListData(CategoryItem item) {
        ProductListState pdState = new ProductListState();
        pdState.categoryId = item.id;
        mediator.setProductListState(pdState);
        view.get().navigateToNextActivity(ProductListActivity.class);
    }

    @Override
    public void loginButtonPressed() {
            view.get().navigateToNextActivity(LoginActivity.class);
        }
 }


