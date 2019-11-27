package com.example.tpfinal.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tpfinal.DatabaseHelper;
import com.example.tpfinal.domain.RegisterUser;

public class SignUpModel {
    static DatabaseHelper databaseHelper;
    public static void  register(Context context, RegisterUser user){
        databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase dbWritable =  databaseHelper.getWritableDatabase();
        databaseHelper.saveUser(dbWritable,user);
    }

}
