package com.example.mydietolog.model;

import com.example.mydietolog.R;

public class Exercise {
    private final int _id;
    private final int _imageIcon;
    private final String _title;
    private final String _description;
    private final double _spentCalories;

    public Exercise(){
        _id = 0;
        _imageIcon = R.drawable.ic_home_black_24dp;
        _title = "Условное название";
        _description = "Если вы видите данную запись, значит во время разработки программного обеспечения произошли критические ошибки";
        _spentCalories = -1;
    }

    public Exercise(int id,int imageIcon, String title, String description, double spentCalories){
        _id = id;
        _imageIcon = imageIcon;
        _title = title;
        _description = description;
        _spentCalories = spentCalories;
    }
    public int getID(){return  _id; }
    public int getImage(){
        return _imageIcon;
    }
    public String getTitle(){
        return _title;
    }
    public String getDescription(){
        return _description;
    }
    public double getSpentCalories(){
        return _spentCalories;
    }
}
