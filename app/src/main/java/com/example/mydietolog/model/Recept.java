package com.example.mydietolog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recept {
    private final String _author;
    private final String _title;
    private final String _description;
    private final String _imageIcon;
    private final double _calories;
    private final double _carbohydrates;
    private final double _fats;
    private final double _protein;
    private final List<Ingridient> _ingridients;
    private final List<StepOfRecepts> _steps;


    public Recept(String author, String title, String description, String imageIcon,
                  double calories, double carbohydrates, double fats, double protein) {
        _author = author;
        _calories = calories;
        _carbohydrates = carbohydrates;
        _fats = fats;
        _title = title;
        _description = description;
        _imageIcon = imageIcon;
        _protein = protein;
        _ingridients = new ArrayList<>();
        _steps = new ArrayList<>();
    }

    public List<Ingridient> getIngridients(){
        return _ingridients;
    }
    public List<StepOfRecepts> getSteps(){
        return _steps;
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

    public void addIngridient(Ingridient value){
        _ingridients.add(value);
    }
    public void addIngridients(Ingridient[] values){
        _ingridients.addAll(Arrays.asList(values));
    }
    public void addIngridients(List<Ingridient> values){
        _ingridients.addAll(values);
    }
    public void addStep(StepOfRecepts value){
        _steps.add(value);
    }
    public void addSteps(StepOfRecepts[] value){
        _steps.addAll(Arrays.asList(value));
    }
    public void addSteps(List<StepOfRecepts> value){
        _steps.addAll(value);
    }
}
