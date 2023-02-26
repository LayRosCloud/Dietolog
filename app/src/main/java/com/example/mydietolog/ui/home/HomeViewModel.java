package com.example.mydietolog.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydietolog.data.DataContext;
import com.example.mydietolog.data.ReaderDatabase;
import com.example.mydietolog.model.Exercise;
import com.example.mydietolog.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Exercise>> myList;

    private final List<Exercise> _exercises = new ArrayList<Exercise>();


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        myList = new MutableLiveData<>();

        ReaderDatabase reader = new ReaderDatabase();

        reader.readUser("marimo", new DataContext.DataLoad() {
            @Override
            public void loaded(User user) {
                myList.setValue(user.getListExercises());
            }
        });

        mText.setValue("МОИ ТРЕНИРОВКИ");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<Exercise>> getList() {
        return myList;
    }
}