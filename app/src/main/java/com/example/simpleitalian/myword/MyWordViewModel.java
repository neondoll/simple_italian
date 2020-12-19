package com.example.simpleitalian.myword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyWordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyWordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 2 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}