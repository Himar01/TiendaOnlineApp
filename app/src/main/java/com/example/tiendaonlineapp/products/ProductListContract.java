package com.example.tiendaonlineapp.products;

import com.example.tiendaonlineapp.data.ProductItem;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProductListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(ProductListViewModel viewModel);

        void navigateToNextActivity(Class c);

        void userLogged(String username);

        void userLogout();


    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onBackPressed();

        void fetchCategoryListData();

        void selectCategoryListData(ProductItem item);

        void onResume();

        void likeButtonPressed();

        void logoutButtonPressed();

        void loginButtonPressed();
    }

    interface Model {
        void fetchProductListData(int categoryId,
                RepositoryContract.GetProductListCallback callback);
    }

}