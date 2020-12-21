package com.example.simpleitalian.learnword;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.simpleitalian.DBWords;
import com.example.simpleitalian.Word;

import java.util.ArrayList;

public class LearnWordViewModel extends ViewModel {
    private final DBWords DBConnector;

    public LearnWordViewModel(Context context) {
        DBConnector = new DBWords(context);
    }

    public ArrayList<Word> selectAllNoKnown() {
        return DBConnector.selectAllWhereKnown(false);
    }

    public void setKnown(Word word) {
        word.setKnown(true);
        DBConnector.update(word);
    }
}