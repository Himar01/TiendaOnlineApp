package com.example.tiendaonlineapp.products;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
import com.example.tiendaonlineapp.categories.CategoryListContract;
import com.example.tiendaonlineapp.categories.CategoryListModel;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class ProductListScreen {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void configure(ProductListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        UserLog userLog = UserLog.getInstance();
        ProductListContract.Presenter presenter = new ProductListPresenter(mediator,userLog);

        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        ProductListModel model = new ProductListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}