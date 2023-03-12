package com.example.mydietolog.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Ingridient {
    private final String _name;
    private final int _number;
    private final int _unitID;

    public Ingridient(String name, int number, int unitID){
        _name = name;
        _number = number;
        _unitID = unitID;
    }

    public String getName(){
        return _name;
    }

    public int getNumber(){
        return _number;
    }

    public int getUnit(){
        return _unitID;
    }

    public String getNameUnit(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT Name FROM Unit WHERE UnitID = " + _unitID, null);
        cursor.moveToNext();
        return cursor.getString(0);
    }
}
