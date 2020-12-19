package com.example.simpleitalian.addnewword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddNewWordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddNewWordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 1 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}