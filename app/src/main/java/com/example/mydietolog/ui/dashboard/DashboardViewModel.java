package com.example.mydietolog.ui.dashboard;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydietolog.data.Contants;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> mHeight;
    private final MutableLiveData<String> mWeight;
    private final MutableLiveData<String> mAge;
    private final MutableLiveData<String> mSpentCalories;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mHeight = new MutableLiveData<>();
        mWeight = new MutableLiveData<>();
        mAge = new MutableLiveData<>();
        mSpentCalories = new MutableLiveData<>();

        mText.setValue("ВАШ ПРОФИЛЬ");
        mHeight.setValue(String.valueOf(Contants.CurrentUser.getHeight()));
        mWeight.setValue(String.valueOf(Contants.CurrentUser.getWeight()));
        mAge.setValue(String.valueOf(Contants.CurrentUser.getAge()));
        mSpentCalories.setValue(String.valueOf(Math.round(Contants.CurrentUser.getSpentCalories())));
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<String> getHeight() { return mHeight;}
    public LiveData<String> getWeight() { return mWeight;}
    public LiveData<String> getAge() { return mAge; }
    public LiveData<String> getSpentCalories() {
        return mSpentCalories;
    }


}