package com.example.tiendaonlineapp.product;

import static java.lang.String.valueOf;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tiendaonlineapp.R;

public class ProductDetailActivity
        extends AppCompatActivity implements ProductDetailContract.View {


    //public static String TAG = ProductDetailActivity.class.getSimpleName();
    public static String TAG = "TiendaOnlineApp.ProductDetailActivity";

    private ProductDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
        // Log.e(TAG, "onCreate()");

        // do the setup
        ProductDetailScreen.configure(this);
        presenter.fetchProductDetail();
    }

    @Override
    public void displayData(ProductDetailViewModel viewModel) {
        //Log.e(TAG, "onViewModelDataUpdated()");

        // deal with the data
        ((TextView) findViewById(R.id.productName)).setText(viewModel.currentProduct.product);
        String priceTotal = valueOf(viewModel.currentProduct.price);
        String[] price = priceTotal.split("\\.");
        ((TextView) findViewById(R.id.productPrice)).setText(price[0]+","+price[1]+"€");
        String details = viewModel.currentProduct.details;
        SpannableString indentedDetails = createIndentedText(details,0,100);
        ((TextView) findViewById(R.id.productDetails)).setText(indentedDetails);
        int img = presenter.fetchImage();
        if(img!=-1)
            ((ImageView) findViewById(R.id.productImage)).setImageResource(img);

    }


    private SpannableString createIndentedText(String text, int marginFirstLine, int marginNextLines) {
        text = text.replace("\t", "   ");
        SpannableString result = new SpannableString(text);
        result.setSpan(new LeadingMarginSpan.Standard(marginFirstLine, marginNextLines),0,text.length(),0);
        return result;
    }




    @Override
    public void injectPresenter(ProductDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
}