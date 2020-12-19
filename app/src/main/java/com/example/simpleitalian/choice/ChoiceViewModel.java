package com.example.simpleitalian.choice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChoiceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChoiceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is 2 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}