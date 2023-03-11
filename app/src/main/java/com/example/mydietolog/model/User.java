package com.example.mydietolog.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@IgnoreExtraProperties
public class User {
    private int _id;
    private String _login;
    private String _email;
    private String _password;
    private int _age;
    private double _weight;
    private double _height;
    private double _spentCalories;
    private int _sex;
    private int _activity;

    public User() throws Exception{
        _id = 0;
        setLogin("this is problem User");
        setEmail("@ru.ru");
        setPassword("1234567890");
        setAge(0);
        setWeight(0.0);
        setHeight(0.0);
        _activity = 0;
    }

    public User(int id,String login, String email, String password, int age, double weight, double height, double spentCalories,int sex, int activity){
        _id = id;
        setLogin(login);
        setEmail(email);
        setPassword(password);
        try {
            setAge(age);
            setWeight(weight);
            setHeight(height);
            setSpentCalories(spentCalories);
            setActivity(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        _sex = sex;
    }
    public int getSex(){return _sex;}
    public int getId(){
        return _id;
    }
    public void setLogin(String value){
        _login = value;
    }
    public String getLogin(){
        return _login;
    }
    public void setEmail(String value){
        _email = value;
    }
    public String getEmail(){
        return _email;
    }
    public void setPassword(String value){
        _password = value;
    }
    public String getPassword(){
        return _password;
    }
    public void setAge(int value) throws Exception{
        if(value < 0 || value > 200){
            throw new Exception("Значение возраста невозможно");
        }

        _age = value;
    }
    public int getAge(){
        return _age;
    }
    public void setWeight(double value) throws Exception{
        if(value < 0 || value > 1000){
            throw new Exception("Значение веса невозможно");
        }

        _weight = value;
    }
    public double getWeight(){
        return _weight;
    }
    public void setHeight(double value) throws Exception{
        if(value < 0 || value > 400){
            throw new Exception("Значение роста невозможно");
        }
        _height = value;
    }
    public double getHeight(){
        return  _height;
    }

    public double getSpentCalories(){
        return _spentCalories;
    }

    public void setSpentCalories(double value) throws Exception{
        if(value < 0 || value > 30000){
            throw new Exception();
        }
        _spentCalories = value;
    }

    public void setActivity(int value) throws  Exception{
        if(value < 0 || value > 4){
            throw new Exception("Ошибка!");
        }
        _activity = value;
    }
    public int getActivity(){
        return _activity;
    }

}
