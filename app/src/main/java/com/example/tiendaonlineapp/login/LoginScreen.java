package com.example.tiendaonlineapp.login;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.app.UserLog;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class LoginScreen {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        UserLog userLog = UserLog.getInstance();
        LoginContract.Presenter presenter = new LoginPresenter(mediator,userLog);

        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        LoginContract.Model model = new LoginModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}