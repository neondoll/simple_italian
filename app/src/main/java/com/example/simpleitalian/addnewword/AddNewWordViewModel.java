package com.example.simpleitalian.addnewword;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.simpleitalian.DBWords;

public class AddNewWordViewModel extends ViewModel {
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final DBWords DBConnector;

    public AddNewWordViewModel(Context context) {
        this.context = context;
        DBConnector = new DBWords(context);
    }

    public boolean addNewWord(String italian, String russian, String transcription) {
        boolean check = false;
        String message;
        if (!italian.equals("") && !russian.equals("") && !transcription.equals("")) {
            if (!DBConnector.isInDatabase(italian, russian)) {
                DBConnector.insert(italian, russian, transcription, 0, 0);
                message = "Успешно!";
                check = true;
            } else message = "Такая запись уже есть!";
        } else message = "Заполните все поля!";
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        return check;
    }
}