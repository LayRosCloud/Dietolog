package com.example.mydietolog.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final int _id;
    private final String _name;

    public Category(int id, String name){
        _id = id;
        _name = name;
    }

    public int getId(){
        return _id;
    }
    public String getName(){
        return _name;
    }

    public static List<String> getNames(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT Name FROM Category", null);
        List<String> categoryList = new ArrayList<>();
        while (cursor.moveToNext()){
            categoryList.add(cursor.getString(0));
        }
        return categoryList;
    }
    public static List<Category> getAll(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM Category", null);
        List<Category> categoryList = new ArrayList<>();
        while (cursor.moveToNext()){
            categoryList.add(new Category(cursor.getInt(0), cursor.getString(1)));
        }
        return categoryList;
    }
}
