package com.example.tiendaonlineapp.product;

import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public interface ProductDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(ProductDetailViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void fetchProductDetail();

        int fetchImage();
    }

    interface Model {
        void fetchProductListData(int productId, RepositoryContract.GetProductCallback callback);
    }

}