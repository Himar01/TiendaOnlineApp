package com.example.tiendaonlineapp.products;

import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.categories.CategoryListContract;
import com.example.tiendaonlineapp.categories.CategoryListModel;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class ProductListScreen {

    public static void configure(ProductListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        ProductListContract.Presenter presenter = new ProductListPresenter(mediator);

        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        ProductListModel model = new ProductListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}