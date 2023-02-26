package com.example.mydietolog.model;

public class Ingridient {
    private final String _name;
    private final int _number;
    private final String _unit;

    public Ingridient(String name, int number, String unit){
        _name = name;
        _number = number;
        _unit = unit;
    }

    public String getName(){
        return _name;
    }

    public int getNumber(){
        return _number;
    }

    public String getUnit(){
        return _unit;
    }
}
