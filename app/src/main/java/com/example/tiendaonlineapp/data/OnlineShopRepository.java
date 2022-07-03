package com.example.tiendaonlineapp.data;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.example.tiendaonlineapp.database.CategoryDao;
import com.example.tiendaonlineapp.database.FavoriteDao;
import com.example.tiendaonlineapp.database.OnlineShopDatabase;
import com.example.tiendaonlineapp.database.ProductDao;
import com.example.tiendaonlineapp.database.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OnlineShopRepository implements RepositoryContract{

    public static String TAG = OnlineShopRepository.class.getSimpleName();

    public static final String DB_FILE = "catalog.db";
    public static final String JSON_FILE = "onlineshop.json";
    public static final String JSON_ROOT = "categories";
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe


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
    private UserDao getUserDao() { return database.userDao();}
    private FavoriteDao getFavoriteDao(){return database.favoriteDao();}

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
    public void clearAllTables(){
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                database.clearAllTables();
            }
        });
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

    @Override
    public void checkUsernameValid(String username, CheckUsernameCallback callback){
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if(callback != null) {
                    callback.onUsernameChecked(getUserDao().checkUsernameExists(username)==null);
                }
            }
        });
    }
    @Override
    public void insertUsername(User user,
                               RepositoryContract.InsertUsernameCallback callback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(callback != null) {
                    getUserDao().insertUser(user);
                    callback.onUsernameInserted();
                }
            }
        });
    }

    @Override
    public void loginUser(User user, RepositoryContract.LoginUsername callback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(callback != null) {
                    if(getUserDao().checkUsernameValid(user.username,user.hashedPassword)!=null){
                        //Generating token that will be used to request user information further
                        String token = generateNewToken();
                        user.token = token;
                        getUserDao().updateUser(user);
                        callback.onUsernameChecked(token);
                    }else{
                        callback.onUsernameChecked(null);
                    }
                }
            }
        });
    }

    @Override
    public void insertFavoriteItem(FavoriteItem item, String token, InsertFavoriteItemCallback callback) {
        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){
                if(getUserDao().checkTokenValid(item.username, token)!=null) {
                    if (callback != null) {
                        getFavoriteDao().insertFavoriteItem(item);
                        callback.FavoriteItemInserted();
                    }
                }
            }
        });
    }

    @Override
    public void deleteFavoriteItem(FavoriteItem item, String token, DeleteFavoriteItemCallback callback) {
        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){
                if(getUserDao().checkTokenValid(item.username, token)!=null) {
                    if (callback != null) {
                        getFavoriteDao().deleteFavoriteItem(item);
                        callback.FavoriteItemDeleted();
                    }
                }
            }
        });
    }

    @Override
    public void checkFavoriteItem(FavoriteItem item, String token, CheckFavoriteItemCallback callback) {
        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){
                if(callback!=null){
                    if(getUserDao().checkTokenValid(item.username, token)!=null){
                        callback.FavoriteItemChecked(getFavoriteDao().checkFavoriteItem(item.username,item.productId)!=null);
                    }
                }
            }
        });
    }

    @Override
    public void getUserProducts(String username, int categoryId, String token, GetUserProductsCallback callback) {
        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){
                if(callback!=null){

                    callback.setUserProducts(
                            getFavoriteDao().loadFavoriteItems(username,categoryId,token));
                }
            }
        });
    }

    @Override
    public void getCategoryList(String username, String token, GetCategoryListCallback callback) {
        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){
                if(callback!=null){

                    callback.setCategoryList(
                            getCategoryDao().loadCategories(username,token));
                }
            }
        });
    }


    private String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);

    }


}
