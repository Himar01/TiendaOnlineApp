package com.example.tiendaonlineapp.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.tiendaonlineapp.database.CategoryDao;
import com.example.tiendaonlineapp.database.OnlineShopDatabase;
import com.example.tiendaonlineapp.database.ProductDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class OnlineShopRepository implements RepositoryContract{

    public static String TAG = OnlineShopRepository.class.getSimpleName();

    public static final String DB_FILE = "catalog.db";
    public static final String JSON_FILE = "onlineshop.json";
    public static final String JSON_ROOT = "categories";

    private static OnlineShopRepository INSTANCE;

    private OnlineShopDatabase database;
    private Context context;

    public static RepositoryContract getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new OnlineShopRepository(context);
        }
        return INSTANCE;

    }

    private OnlineShopRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(
                context, OnlineShopDatabase.class, DB_FILE
        ).build();
    }
    private CategoryDao getCategoryDao() {
        return database.categoryDao();
    }

    private ProductDao getProductDao() {
        return database.productDao();
    }
    private boolean loadCatalogFromJSON(String json) {
        Log.e(TAG, "loadCatalogFromJSON()");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

            if (jsonArray.length() > 0) {

                final List<CategoryItem> categories = Arrays.asList(
                        gson.fromJson(jsonArray.toString(), CategoryItem[].class)
                );

                for (CategoryItem category: categories) {
                    getCategoryDao().insertCategory(category);
                }

                for (CategoryItem category: categories) {
                    for (ProductItem product: category.items) {
                        product.categoryId = category.id;
                        getProductDao().insertProduct(product);
                    }
                }

                return true;
            }

        } catch (JSONException error) {
            Log.e(TAG, "error: " + error);
        }

        return false;
    }
    private String loadJSONFromAsset() {
        //Log.e(TAG, "loadJSONFromAsset()");

        String json = null;

        try {

            InputStream is = context.getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException error) {
            Log.e(TAG, "error: " + error);
        }

        return json;
    }

    @Override
    public void loadCatalog(
            final boolean clearFirst, final FetchCatalogDataCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if(clearFirst) {
                    database.clearAllTables();
                }

                boolean error = false;
                if(getCategoryDao().loadCategories().size() == 0 ) {
                    error = !loadCatalogFromJSON(loadJSONFromAsset());
                }

                if(callback != null) {
                    callback.onCatalogDataFetched(error);
                }
            }
        });
    }

    @Override
    public void getProductList(CategoryItem category, GetProductListCallback callback) {

    }

    @Override
    public void getProductList(int categoryId, GetProductListCallback callback) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if(callback != null) {
                    callback.setProductList(getProductDao().loadProducts(categoryId));
                }
            }
        });
    }

    @Override
    public void getProduct(int id, GetProductCallback callback) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if(callback != null) {
                    callback.setProduct(getProductDao().loadProduct(id));
                }
            }
        });
    }

    @Override
    public void getCategory(int id, GetCategoryCallback callback) {

    }

    @Override
    public void getCategoryList(final GetCategoryListCallback callback) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if(callback != null) {
                    callback.setCategoryList(getCategoryDao().loadCategories());
                }
            }
        });

    }

    @Override
    public void deleteProduct(ProductItem product, DeleteProductCallback callback) {

    }

    @Override
    public void updateProduct(ProductItem product, UpdateProductCallback callback) {

    }

    @Override
    public void deleteCategory(CategoryItem category, DeleteCategoryCallback callback) {

    }

    @Override
    public void updateCategory(CategoryItem category, UpdateCategoryCallback callback) {

    }
}
