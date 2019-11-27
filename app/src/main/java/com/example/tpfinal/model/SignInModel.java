package com.example.tpfinal.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpfinal.DatabaseHelper;
import com.example.tpfinal.domain.RegisterUser;

public class SignInModel {
    static DatabaseHelper databaseHelper;
    public static boolean authenticate(Context context, String formEmail, String formPassword){
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbReadable =  databaseHelper.getReadableDatabase();
        RegisterUser user = databaseHelper.loadUser(dbReadable,formEmail,formPassword);
        return user!=null;
    }

    public static RegisterUser getAuthenticateUser(Context context, String formEmail, String formPassword){
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbReadable =  databaseHelper.getReadableDatabase();
        RegisterUser user = databaseHelper.loadUser(dbReadable,formEmail,formPassword);
        return user;
    }
}
