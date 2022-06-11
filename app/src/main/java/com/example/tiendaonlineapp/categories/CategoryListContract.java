package com.example.tiendaonlineapp.categories;

import com.example.tiendaonlineapp.data.CategoryItem;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface CategoryListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayCategoryListData(CategoryListViewModel viewModel);

        void navigateToProductListScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        //void injectRouter(Router router);

        void fetchCategoryListData();
        void selectCategoryListData(CategoryItem item);
    }

    interface Model {
        void fetchCategoryListData(
                RepositoryContract.GetCategoryListCallback callback);
    }

}