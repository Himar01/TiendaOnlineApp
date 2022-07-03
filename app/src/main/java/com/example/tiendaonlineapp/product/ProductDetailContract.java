package com.example.tiendaonlineapp.product;

import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.login.LoginActivity;

import java.lang.ref.WeakReference;

public interface ProductDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(ProductDetailViewModel viewModel);

        void userLogged(String username);

        void userLogout();

        void navigateToNextActivity(Class<LoginActivity> c);

        void showToastAnimation(int message, boolean isGood);

        void changeToggleButtonState();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void fetchProductDetail();

        void onResume();

        void logoutButtonPressed();

        void loginButtonPressed();

        void toggleButtonPressed(boolean isChecked);
    }

    interface Model {
        void fetchProductListData(int productId, RepositoryContract.GetProductCallback callback);

        void insertFavItem(int productId, String username, String token, RepositoryContract.InsertFavoriteItemCallback callback);

        void deleteFavItem(int productId, String username, String token, RepositoryContract .DeleteFavoriteItemCallback callback);

        void checkFavItemExists(int productId, String username, String token, RepositoryContract.CheckFavoriteItemCallback callback);
    }

}