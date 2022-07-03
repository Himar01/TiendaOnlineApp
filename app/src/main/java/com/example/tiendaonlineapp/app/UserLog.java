package com.example.tiendaonlineapp.app;

import com.example.tiendaonlineapp.data.User;

public class UserLog{
    private static UserLog INSTANCE;
    public User user = null;

    public static UserLog getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLog();
        }
        return INSTANCE;
    }
    public static void resetInstance() {
        INSTANCE = null;
    }
}
