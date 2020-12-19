package com.example.simpleitalian.learnword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LearnWordViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public LearnWordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is learn word fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}