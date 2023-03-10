package com.example.mydietolog.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int SCHEMA = 1;
    private static final String DATABASE_NAME = "app.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(Contants.ConstantsSQL.CREATOR.TABLE_USER);
        db.execSQL(Contants.ConstantsSQL.CREATOR.TABLE_EXERCISE);
        db.execSQL(Contants.ConstantsSQL.CREATOR.TABLE_EXERCISE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL(Contants.ConstantsSQL.Dropper.DROP_EXERCISE_USER);
        db.execSQL(Contants.ConstantsSQL.Dropper.DROP_USERS);
        db.execSQL(Contants.ConstantsSQL.Dropper.DROP_EXERCISE);
        onCreate(db);
    }
    public void insertInUser(String login, int age, double weight, double height){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Contants.ConstantsSQL.User.LOGIN, login);
        cv.put(Contants.ConstantsSQL.User.AGE, age);
        cv.put(Contants.ConstantsSQL.User.WEIGHT, weight);
        cv.put(Contants.ConstantsSQL.User.HEIGHT, height);

        db.insert(Contants.ConstantsSQL.User.TABLE, null, cv);
    }
}
