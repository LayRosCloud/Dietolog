package com.example.mydietolog.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Unit {
    private final int _id;
    private final String _name;

    public Unit(int id, String name){
        _id = id;
        _name = name;
    }

    public int getId(){
        return _id;
    }
    public String getName(){
        return _name;
    }

    public static List<String> findNames(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT Name FROM UNIT", null);
        List<String> unitList = new ArrayList<>();
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            unitList.add(name);
        }
        return unitList;
    }
    public static List<Unit> findUnits(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM UNIT", null);
        List<Unit> unitList = new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            unitList.add(new Unit(id, name));
        }
        return unitList;
    }
}
