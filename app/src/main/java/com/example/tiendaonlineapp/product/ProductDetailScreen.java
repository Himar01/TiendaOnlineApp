package com.example.tiendaonlineapp.product;

import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class
ProductDetailScreen {

    public static void configure(ProductDetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        ProductDetailContract.Presenter presenter = new ProductDetailPresenter(mediator);

        String data = context.get().getString(R.string.app_name);
        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        ProductDetailContract.Model model = new ProductDetailModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}