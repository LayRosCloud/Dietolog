package com.example.mydietolog.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ReceptIngredients {
    private final Recept _currentRecept;
    private final List<Ingridient> _ingredients;

    public ReceptIngredients(@NonNull final Recept recept){
        _currentRecept = recept;
        _ingredients = new ArrayList<>();
    }

    public void findAllIngredient(SQLiteDatabase db){
        final Cursor cursor = db.rawQuery(
                "SELECT Name, Number, UnitID FROM ReceptsIngredient WHERE ReceptID = " + _currentRecept.getId(),null);

        while(cursor.moveToNext()){
            final String name = cursor.getString(0);
            final int number = cursor.getInt(1);
            final int unitID = cursor.getInt(2);

            final Ingridient ingridient = new Ingridient(name, number, unitID);

            _ingredients.add(ingridient);
        }
    }
    public List<Ingridient> getIngredients(){
        return _ingredients;
    }
}
