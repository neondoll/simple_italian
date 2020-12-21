package com.example.simpleitalian.myword;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.simpleitalian.DBWords;
import com.example.simpleitalian.Word;

import java.util.ArrayList;

public class MyWordViewModel extends ViewModel {
    private final DBWords DBConnector;

    public MyWordViewModel(Context context) {
        DBConnector = new DBWords(context);
    }

    public ArrayList<Word> selectAll() {
        return DBConnector.selectAllOrderBy("italian");
    }
}