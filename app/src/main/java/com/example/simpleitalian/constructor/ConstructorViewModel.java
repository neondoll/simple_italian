package com.example.simpleitalian.constructor;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.simpleitalian.DBWords;
import com.example.simpleitalian.Word;

import java.util.ArrayList;

public class ConstructorViewModel extends ViewModel {
    private final DBWords DBConnector;

    public ConstructorViewModel(Context context) {
        DBConnector = new DBWords(context);
    }

    public ArrayList<Word> selectAllKnown() {
        return DBConnector.selectAllWhereKnown(true);
    }

    public ArrayList<Word> selectAllNoKnown() {
        return DBConnector.selectAllWhereKnown(false);
    }

    public void setKnown(Word word) {
        word.setKnown(false);
        DBConnector.update(word);
    }
}