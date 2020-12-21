package com.example.simpleitalian.learnword;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentLearnWordBinding;

import java.util.ArrayList;
import java.util.Random;

public class LearnWordFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Word> words;
    private Button buttonKnow;
    private Button buttonNoKnow;
    private Button buttonSpeaker;
    private FragmentLearnWordBinding binding;
    private LearnWordViewModel viewModel;
    private ImageView image;
    private TextView textItalian;
    private TextView textRussian;
    private TextView textTranscription;
    private Word currentWord;
    private int item;
    private final int sizeText = 24;
    private final int unit = TypedValue.COMPLEX_UNIT_SP;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //learnWordViewModel = new ViewModelProvider(this).get(LearnWordViewModel.class);
        viewModel = new LearnWordViewModel(getActivity());

        words = new ArrayList<>();
        words.add(new Word(0, "", "", "", 0, 0, false));
        words.addAll(viewModel.selectAllNoKnown());

        item = getRandInt(words.size() - 1) + 1;
        currentWord = words.get(item);

        binding = FragmentLearnWordBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        buttonKnow = binding.buttonKnow;
        buttonKnow.setOnClickListener(this);

        buttonNoKnow = binding.buttonNoKnow;
        buttonNoKnow.setOnClickListener(this);

        buttonSpeaker = binding.buttonSpeaker;

        image = binding.imageView;

        textItalian = binding.textItalian;
        textItalian.setTextSize(unit, sizeText);

        textRussian = binding.textRussian;
        textRussian.setTextSize(unit, sizeText);

        textTranscription = binding.textTranscription;
        textTranscription.setTextSize(unit, sizeText);

        getViewWord();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private int getRandInt(int max) {
        Random rand = new Random();
        return max > 0 ? rand.nextInt(max) : -1;
    }

    private void getViewWord() {
        textItalian.setText(currentWord.getItalian());

        textRussian.setText(currentWord.getRussian());

        textTranscription.setText(currentWord.getTranscription());

        if (currentWord.getSpeech() == 0) buttonSpeaker.setVisibility(View.GONE);
        else buttonSpeaker.setVisibility(View.VISIBLE);

        if (currentWord.getImage() == 0) image.setVisibility(View.GONE);
        else {
            image.setImageResource(currentWord.getImage());
            image.setVisibility(View.VISIBLE);
        }

        if (item == 0) {
            buttonKnow.setVisibility(View.GONE);
            buttonNoKnow.setVisibility(View.GONE);
            binding.textFinal.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_know:
                viewModel.setKnown(currentWord);
                words.remove(item);
                break;
            case R.id.button_no_know:
                words.get(item).setKnown(false);
                break;
        }

        item = getRandInt(words.size() - 1) + 1;
        currentWord = words.get(item);

        getViewWord();
    }
}
