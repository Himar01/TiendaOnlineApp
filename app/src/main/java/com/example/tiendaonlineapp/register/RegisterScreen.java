package com.example.tiendaonlineapp.register;

import androidx.fragment.app.FragmentActivity;

import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.OnlineShopRepository;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class RegisterScreen {

    public static void configure(RegisterContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);


        AppMediator mediator = AppMediator.getInstance();
        RegisterContract.Presenter presenter = new RegisterPresenter(mediator);

        RepositoryContract repository = OnlineShopRepository.getInstance(context.get());
        RegisterContract.Model model = new RegisterModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}