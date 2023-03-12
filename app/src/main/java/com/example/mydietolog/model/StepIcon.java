package com.example.mydietolog.model;

import android.net.Uri;

import androidx.annotation.NonNull;

public class StepIcon {
    private final int _id;
    private Uri _uri;
    private boolean _loaded;

    public StepIcon(@NonNull final int id, final Uri uri){
        _id = id;
        _uri = uri;
        _loaded = false;
    }

    public int getId(){
        return _id;
    }

    public Uri getUri(){
        return _uri;
    }

    public void setUri(Uri uri){
        _uri = uri;
    }

    public boolean isLoaded(){
       return _loaded;
    }
    public void setLoaded(boolean value){
        _loaded = value;
    }
}
