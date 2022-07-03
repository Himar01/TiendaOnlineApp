package com.example.tiendaonlineapp.categories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;
import com.example.tiendaonlineapp.login.LoginContract;
import com.example.tiendaonlineapp.login.LoginPresenter;

import java.lang.ref.WeakReference;

public class CategoryListScreen {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void configure(CategoryListContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        UserLog userLog = UserLog.getInstance();
        CategoryListContract.Presenter presenter = new CategoryListPresenter(mediator,userLog);
        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        CategoryListContract.Model model = new CategoryListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}