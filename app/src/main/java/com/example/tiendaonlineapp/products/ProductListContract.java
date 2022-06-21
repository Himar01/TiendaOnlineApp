package com.example.tiendaonlineapp.products;

import com.example.tiendaonlineapp.data.ProductItem;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProductListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(ProductListViewModel viewModel);

        void navigateToProductDetailScreen();

    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);


        void onBackPressed();

        void fetchCategoryListData();

        void selectCategoryListData(ProductItem item);

        int[] loadImgResources();

        void onCreate();
    }

    interface Model {
        void fetchProductListData(int categoryId,
                RepositoryContract.GetProductListCallback callback);
    }

}