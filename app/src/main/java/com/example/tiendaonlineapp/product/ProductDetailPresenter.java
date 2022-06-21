package com.example.tiendaonlineapp.product;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.ProductItem;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    //public static String TAG = ProductDetailPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.ProductDetailPresenter";

    private WeakReference<ProductDetailContract.View> view;
    private ProductDetailState state;
    private ProductDetailContract.Model model;
    private AppMediator mediator;

    public ProductDetailPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProductDetailState();

    }
    @Override
    public void fetchProductDetail(){
        model.fetchProductListData(state.productId, new RepositoryContract.GetProductCallback() {
            @Override
            public void setProduct(ProductItem product){
                state.currentProduct = product;
                view.get().displayData(state);
            }
        });
    }

    @Override
    public int fetchImage() {
        switch(state.currentProduct.id){
            case 0:
                return R.drawable.componente_tempest_liquid_cooler;
            case 1:
                return R.drawable.componente_amd_ryzen_5_5600;
            case 2:
                return R.drawable.componente_corsair_vengeance_ddr4_3200_16gb_2x8gb;

            case 11:
                return R.drawable.ordenador_asus_e410ma_eb008ts_intel_celeron;
            default:
                return -1;
        }
    }

    @Override
    public void injectView(WeakReference<ProductDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductDetailContract.Model model) {
        this.model = model;
    }

}