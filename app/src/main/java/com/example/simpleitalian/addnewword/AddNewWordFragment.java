package com.example.simpleitalian.addnewword;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleitalian.DBWords;
import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentAddNewWordBinding;

import java.util.ArrayList;

public class AddNewWordFragment extends Fragment {
    private AddNewWordViewModel addNewWordViewModel;
    private Button buttonAdd;
    private DBWords DBConnector;
    private EditText textItalian, textRussian, textTranscription;
    private FragmentAddNewWordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBConnector = new DBWords(getActivity());

        addNewWordViewModel = new ViewModelProvider(this).get(AddNewWordViewModel.class);
        binding = FragmentAddNewWordBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        buttonAdd = binding.buttonAdd;
        textItalian = binding.textItalian;
        textRussian = binding.textRussian;
        textTranscription = binding.textTranscription;

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                if (
                        !textItalian.getText().toString().equals("") &&
                                !textRussian.getText().toString().equals("") &&
                                !textTranscription.getText().toString().equals("")
                ) {
                    if (!isWordInDatabase(textItalian.getText().toString(), textRussian.getText().toString())) {
                        DBConnector.insert(textItalian.getText().toString(), textRussian.getText().toString(), textTranscription.getText().toString(), 0, 0);

                        textItalian.setText("");
                        textRussian.setText("");
                        textTranscription.setText("");

                        message = "Успешно!";
                    } else message = "Такая запись уже есть!";
                } else message = "Заполните все поля!";

                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isWordInDatabase(String italian, String russian) {
        ArrayList<Word> words = DBConnector.selectAll(italian);
        if (words.size() > 0) {
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getRussian().equals(russian)) {
                    return true;
                }
            }
        }
        return false;
    }
}