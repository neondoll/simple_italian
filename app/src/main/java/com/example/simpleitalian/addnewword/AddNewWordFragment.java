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

import com.example.simpleitalian.databinding.FragmentAddNewWordBinding;

public class AddNewWordFragment extends Fragment {
    private AddNewWordViewModel addNewWordViewModel;
    private Button buttonAdd;
    private EditText textItalian, textRussian, textTranscription;
    private FragmentAddNewWordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
                textItalian.setText("");
                textRussian.setText("");
                textTranscription.setText("");

                new ProgressTask().execute();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... items) {
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getActivity(), "Успешно!", Toast.LENGTH_SHORT).show();
        }
    }
}