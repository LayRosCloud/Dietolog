package com.example.mydietolog.ui.home;

import android.database.Cursor;

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

    private final List<Exercise> _exercises = new ArrayList<>();


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        myList = new MutableLiveData<>();

        mText.setValue("МОИ ТРЕНИРОВКИ");
        myList.setValue(_exercises);
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<Exercise>> getList() {
        return myList;
    }
}