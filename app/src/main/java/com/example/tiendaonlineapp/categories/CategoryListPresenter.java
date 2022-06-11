package com.example.tiendaonlineapp.categories;

import android.util.Log;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.CategoryItem;
import com.example.tiendaonlineapp.data.RepositoryContract;

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

    public void onStart() {
        Log.e(TAG, "onStart()");
    }

    public void onResume() {
        Log.e(TAG, "onResume()");
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
                view.get().displayCategoryListData(state);
            }
        });
    }

    @Override
    public void selectCategoryListData(CategoryItem item) {

    }


}