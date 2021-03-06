package com.example.simpleitalian.addnewword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.simpleitalian.databinding.FragmentAddNewWordBinding;

public class AddNewWordFragment extends Fragment {
    private AddNewWordViewModel viewModel;
    private EditText textItalian;
    private EditText textRussian;
    private EditText textTranscription;
    private FragmentAddNewWordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //addNewWordViewModel = new ViewModelProvider(this).get(AddNewWordViewModel.class);
        viewModel = new AddNewWordViewModel(getActivity());

        binding = FragmentAddNewWordBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        Button buttonAdd = binding.buttonAdd;
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.addNewWord(textItalian.getText().toString(), textRussian.getText().toString(), textTranscription.getText().toString())) {
                    textItalian.setText("");
                    textRussian.setText("");
                    textTranscription.setText("");
                }
            }
        });

        textItalian = binding.textItalian;
        textRussian = binding.textRussian;
        textTranscription = binding.textTranscription;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}