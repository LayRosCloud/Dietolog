package com.example.mydietolog.data;

import androidx.annotation.NonNull;

import com.example.mydietolog.model.Exercise;
import com.example.mydietolog.model.Recept;
import com.example.mydietolog.model.User;

import java.util.Locale;

public class WritableDatabase {
    private final DataContext _data = new DataContext();

    public void write(@NonNull User user){
        user.setId(_data.DataUsers.getKey());

        user.setLogin(user.getLogin().toLowerCase(Locale.ROOT));

        _data.DataUsers.child(user.getLogin()).child(Contants.User.EMAIL).setValue(user.getEmail());
        _data.DataUsers.child(user.getLogin()).child(Contants.User.PASSWORD).setValue(user.getPassword());
        _data.DataUsers.child(user.getLogin()).child(Contants.User.AGE).setValue(user.getAge());
        _data.DataUsers.child(user.getLogin()).child(Contants.User.WEIGHT).setValue(user.getWeight());
        _data.DataUsers.child(user.getLogin()).child(Contants.User.HEIGHT).setValue(user.getHeight());
        _data.DataUsers.child(user.getLogin()).child(Contants.User.SPENT_CALORIES).setValue(user.getSpentCalories());
    }
    public void write(@NonNull User user, @NonNull Exercise exercise){

        user.setLogin(user.getLogin().toLowerCase(Locale.ROOT));
        _data.DataUsers.child(user.getLogin()).child(Contants.User.EXERCISES).push().setValue(exercise);
    }
    public void write(@NonNull Recept recept){

    }
}
