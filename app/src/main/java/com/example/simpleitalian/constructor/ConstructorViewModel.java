package com.example.simpleitalian.constructor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConstructorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConstructorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 2 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}