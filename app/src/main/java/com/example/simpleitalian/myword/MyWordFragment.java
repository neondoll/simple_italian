package com.example.simpleitalian.myword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleitalian.databinding.FragmentMyWordBinding;

public class MyWordFragment extends Fragment {
    private MyWordViewModel myWordViewModel;
    private FragmentMyWordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myWordViewModel = new ViewModelProvider(this).get(MyWordViewModel.class);

        binding = FragmentMyWordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textAddNewWord;
        addNewWordViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
