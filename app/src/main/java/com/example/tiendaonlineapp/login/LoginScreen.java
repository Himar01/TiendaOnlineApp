package com.example.tiendaonlineapp.login;

import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        LoginContract.Presenter presenter = new LoginPresenter(mediator);

        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        LoginContract.Model model = new LoginModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}