package com.example.tiendaonlineapp.categories;

import com.example.tiendaonlineapp.data.CategoryItem;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface CategoryListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(CategoryListViewModel viewModel, boolean likedButton);

        void navigateToNextActivity(Class c);

        void userLogged(String username);
        void userLogout();

        boolean likeButtonIsChecked();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onCreate();
        void selectCategoryListData(CategoryItem item);
        void loginButtonPressed();

        void onResume();

        void logoutButtonPressed();

        void likeButtonPressed();
    }

    interface Model {
        void fetchCategoryListData(
                RepositoryContract.GetCategoryListCallback callback);

        void fetchUserProductListData(String username, String token, RepositoryContract.GetCategoryListCallback callback);

        void clearAllTables();
    }

}