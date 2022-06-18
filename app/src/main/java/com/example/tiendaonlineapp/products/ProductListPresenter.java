package com.example.tiendaonlineapp.products;

import android.util.Log;

import com.example.tiendaonlineapp.R;
import com.example.tiendaonlineapp.app.AppMediator;
import com.example.tiendaonlineapp.data.ProductItem;
import com.example.tiendaonlineapp.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter {

    //public static String TAG = ProductListPresenter.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.ProductListPresenter";

    private WeakReference<ProductListContract.View> view;
    private ProductListState state;
    private ProductListContract.Model model;
    private AppMediator mediator;

    public ProductListPresenter(AppMediator mediator) {
        this.mediator = mediator;
        state = mediator.getProductListState();
        //state = mediator.getProductListState();
    }


    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void fetchCategoryListData() {
        // Log.e(TAG, "fetchCategoryListData()");

        // call the model
        model.fetchProductListData(state.categoryId,new RepositoryContract.GetProductListCallback() {
            @Override
            public void setProductList(List<ProductItem> products) {
                state.products = products;
                view.get().displayData(state);
            }
        });
    }

    @Override
    public void selectCategoryListData(ProductItem item) {

    }
    @Override
    public int[] loadImgResources(){
        int[] imgResources = new int[]{};
        switch(state.categoryId){
            case 0:
                imgResources = new int[]{R.drawable.componente_tempest_liquid_cooler,
                        R.drawable.componente_amd_ryzen_5_5600,
                        R.drawable.componente_corsair_vengeance_ddr4_3200_16gb_2x8gb};
                break;
            case 1:
                imgResources = new int[]{R.drawable.ordenador_asus_e410ma_eb008ts_intel_celeron};
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
        return imgResources;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void injectView(WeakReference<ProductListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(ProductListContract.Model model) {
        this.model = model;
    }

}