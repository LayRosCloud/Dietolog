package com.example.mydietolog.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mydietolog.model.Recept;

import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Recept>> _recepts;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        _recepts = new MutableLiveData<>();
        mText.setValue("РЕЦЕПТЫ");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<Recept>> getList() {
        return _recepts;
    }
}