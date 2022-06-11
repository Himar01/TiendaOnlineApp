package com.example.tiendaonlineapp.categories;

import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class CategoryListScreen {

    public static void configure(CategoryListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        CategoryListContract.Presenter presenter = new CategoryListPresenter(mediator);
        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        CategoryListContract.Model model = new CategoryListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}