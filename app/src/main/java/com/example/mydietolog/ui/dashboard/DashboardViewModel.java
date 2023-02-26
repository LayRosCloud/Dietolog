package com.example.mydietolog.ui.dashboard;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ВАШ ПРОФИЛЬ");
    }

    public LiveData<String> getText() {
        return mText;
    }


}