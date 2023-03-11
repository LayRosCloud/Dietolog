package com.example.mydietolog.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.mydietolog.data.Contants;

import java.util.ArrayList;
import java.util.List;

public class UserExercises {
    private final User _currentUser;
    private final List<Exercise> _exercises;

    public UserExercises(@NonNull User user){
        _currentUser = user;
        _exercises = new ArrayList<>();
    }

    public UserExercises findExercises(@NonNull SQLiteDatabase db){
        _exercises.clear();
        Cursor cursor =
                db.rawQuery(
                "SELECT e._id, e.Name, e.Description, e.SpentCalories, e.ImageIcon\n" +
                "FROM ExerciseUser eu INNER JOIN Exercise e ON eu.ExerciseID = e._id\n" +
                "WHERE UserID = " + _currentUser.getId(),
                null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            double spentCalories = cursor.getDouble(3);
            int image = cursor.getInt(4);

            Exercise exercise = new Exercise(id, image, name, description, spentCalories);
            _exercises.add(exercise);
        }

        return this;
    }

    public List<Exercise> getExercises(){
        return _exercises;
    }

    public void addExercise(@NonNull SQLiteDatabase db, Exercise exercise, int number){
        _exercises.add(exercise);
        ContentValues cv = new ContentValues();
        cv.put("UserID", _currentUser.getId());
        cv.put("ExerciseID", exercise.getID());
        cv.put("Number", number);
        db.insert(Contants.SQL.TABLE_EXERCISE_USER, null, cv);
    }

    public void clear(@NonNull SQLiteDatabase db){
        _exercises.clear();
        db.execSQL("DELETE TABLE ExerciseUser WHERE UserID = " + _currentUser.getId());
    }
}
