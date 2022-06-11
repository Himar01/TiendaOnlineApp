package com.example.tiendaonlineapp.app;

import com.example.tiendaonlineapp.categories.CategoryListState;

public class AppMediator {

    private static AppMediator INSTANCE;

    private CategoryListState categoryListState = new CategoryListState();


    private AppMediator() {

    }


    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }


    public static void resetInstance() {
        INSTANCE = null;
    }

    public CategoryListState getCategoryListState() {
        return categoryListState;
    }

    public void setScreenState(CategoryListState state) {
        categoryListState = state;
    }



}
