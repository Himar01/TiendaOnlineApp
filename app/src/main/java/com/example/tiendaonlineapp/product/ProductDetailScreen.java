package com.example.tiendaonlineapp.product;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class
ProductDetailScreen {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void configure(ProductDetailContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        UserLog userLog = UserLog.getInstance();
        ProductDetailContract.Presenter presenter = new ProductDetailPresenter(mediator,userLog);

        String data = context.get().getString(R.string.app_name);
        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        ProductDetailContract.Model model = new ProductDetailModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}