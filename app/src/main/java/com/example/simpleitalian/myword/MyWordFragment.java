package com.example.simpleitalian.myword;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentMyWordBinding;

import java.util.ArrayList;

public class MyWordFragment extends Fragment {
    private FragmentMyWordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //myWordViewModel = new ViewModelProvider(this).get(MyWordViewModel.class);
        MyWordViewModel viewModel = new MyWordViewModel(getActivity());
        ArrayList<Word> words = viewModel.selectAll();

        binding = FragmentMyWordBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        TableLayout table = binding.table;
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow head = new TableRow(getContext());

        TextView headColumn1 = new TextView(getContext());
        headColumn1.setText("Итальянский");
        headColumn1.setTypeface(Typeface.DEFAULT_BOLD);

        TextView headColumn2 = new TextView(getContext());
        headColumn2.setText("Транскрипция");
        headColumn2.setTypeface(Typeface.DEFAULT_BOLD);

        TextView headColumn3 = new TextView(getContext());
        headColumn3.setText("Перевод");
        headColumn3.setTypeface(Typeface.DEFAULT_BOLD);

        head.addView(headColumn1);
        head.addView(headColumn2);
        head.addView(headColumn3);

        table.addView(head);

        TableRow body;
        TextView bodyColumn1, bodyColumn2, bodyColumn3;
        for (int i = 0; i < words.size(); i++) {
            body = new TableRow(getContext());

            bodyColumn1 = new TextView(getContext());
            bodyColumn1.setText(words.get(i).getItalian());

            bodyColumn2 = new TextView(getContext());
            bodyColumn2.setText(words.get(i).getTranscription());

            bodyColumn3 = new TextView(getContext());
            bodyColumn3.setText(words.get(i).getRussian());

            body.addView(bodyColumn1);
            body.addView(bodyColumn2);
            body.addView(bodyColumn3);

            table.addView(body);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
