package com.example.mydietolog.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recept {
    private final int _id;
    private final String _author;
    private final String _title;
    private final String _description;
    private final String _imageIcon;
    private final double _calories;
    private final double _carbohydrates;
    private final double _fats;
    private final double _protein;
    private final int _categoryId;


    public Recept(@NonNull int id,
                  @NonNull String author,
                  @NonNull String title,
                  @NonNull String description,
                  @NonNull String imageIcon,
                  @NonNull double calories,
                  @NonNull double carbohydrates,
                  @NonNull double fats,
                  @NonNull double protein,
                  @NonNull int categoryId) {
        _id = id;
        _author = author;
        _calories = calories;
        _carbohydrates = carbohydrates;
        _fats = fats;
        _title = title;
        _description = description;
        _imageIcon = imageIcon;
        _protein = protein;
        _categoryId = categoryId;
    }
    public int getId(){
        return _id;
    }

    public String getAuthor(){
        return _author;
    }

    public double getCalories(){
        return _calories;
    }
    public double getCarbohydrates(){
        return _carbohydrates;
    }
    public double getFats(){
        return _fats;
    }
    public double getProtein(){
        return _protein;
    }
    public String getImage(){
        return _imageIcon;
    }
    public String getDescription(){
        return _description;
    }
    public String getTitle(){
        return _title;
    }

    public static List<Recept> getRecepts(SQLiteDatabase db){
        List<Recept> recepts = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM Recepts", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String imageIcon = cursor.getString(3);
            double calories = cursor.getDouble(4);
            double fats = cursor.getDouble(5);
            double carbohydrates = cursor.getDouble(6);
            double protein = cursor.getDouble(7);
            int category = cursor.getInt(8);
            String author = cursor.getString(9);

            Recept recept = new Recept(id, author, title, description, imageIcon, calories, carbohydrates, fats, protein, category);
            recepts.add(recept);
        }

        return recepts;
    }

}
