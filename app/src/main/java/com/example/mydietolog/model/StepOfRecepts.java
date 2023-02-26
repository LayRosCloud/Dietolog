package com.example.mydietolog.model;

public class StepOfRecepts {
    private final String _imageIcon;
    private final String _title;
    private final String _description;

    public StepOfRecepts(String imageIcon, String title, String description){
        _imageIcon = imageIcon;
        _title = title;
        _description = description;
    }

    public String getImage(){
        return _imageIcon;
    }

    public String getTitle(){
        return _title;
    }

    public String getDescription(){
        return _description;
    }
}
