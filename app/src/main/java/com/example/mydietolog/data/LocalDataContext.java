package com.example.mydietolog.data;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class LocalDataContext extends AppCompatActivity {

    private SharedPreferences sPref;

    public void save(String key, String value){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(key, value);
        ed.apply();
    }

    public String load(String key){
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(key, "");
        return savedText;
    }

}
