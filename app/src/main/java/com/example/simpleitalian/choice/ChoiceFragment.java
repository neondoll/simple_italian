package com.example.simpleitalian.choice;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentChoiceBinding;

import java.util.ArrayList;
import java.util.Random;

public class ChoiceFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Word> wordsChoice;
    private ArrayList<Word> wordsKnown;
    private ArrayList<Word> wordsNoKnown;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonContinue;
    private Button buttonFinish;
    private ChoiceViewModel viewModel;
    private ConstraintLayout layoutProcess;
    private FragmentChoiceBinding binding;
    private ImageView image;
    private LinearLayout layoutStart;
    private TextView textCount;
    private TextView textRussian;
    private Word currentWord;
    private int correctAnswer;
    private int countCorrectAnswers;
    private int item;
    private int countWordsChoice = 7;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //choiceViewModel = new ViewModelProvider(this).get(ChoiceViewModel.class);
        viewModel = new ChoiceViewModel(getActivity());

        binding = FragmentChoiceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        layoutStart = binding.layoutStart;

        layoutProcess = binding.layoutProcess;

        buttonAnswer1 = binding.buttonAnswer1;
        buttonAnswer1.setBackgroundColor(Color.MAGENTA);
        buttonAnswer1.setOnClickListener(this);

        buttonAnswer2 = binding.buttonAnswer2;
        buttonAnswer2.setBackgroundColor(Color.MAGENTA);
        buttonAnswer2.setOnClickListener(this);

        buttonAnswer3 = binding.buttonAnswer3;
        buttonAnswer3.setBackgroundColor(Color.MAGENTA);
        buttonAnswer3.setOnClickListener(this);

        buttonContinue = binding.buttonContinue;
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item++;
                currentWord = wordsChoice.get(item);

                correctAnswer = getRandInt(3);

                getViewWord();

                buttonAnswer1.setBackgroundColor(Color.MAGENTA);
                buttonAnswer2.setBackgroundColor(Color.MAGENTA);
                buttonAnswer3.setBackgroundColor(Color.MAGENTA);

                buttonContinue.setVisibility(View.INVISIBLE);
            }
        });

        buttonFinish = binding.buttonFinish;
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutStart.setVisibility(View.VISIBLE);
                layoutProcess.setVisibility(View.INVISIBLE);
            }
        });

        Button buttonStart = binding.buttonStart;
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        image = binding.imageView;

        textCount = binding.textCount;
        textCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        textRussian = binding.textRussian;
        textRussian.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        boolean check = true;
        switch (v.getId()) {
            case R.id.buttonAnswer1:
                switch (correctAnswer) {
                    case 0:
                        buttonAnswer1.setBackgroundColor(Color.rgb(4, 180, 4));
                        buttonAnswer2.setBackgroundColor(Color.WHITE);
                        buttonAnswer3.setBackgroundColor(Color.WHITE);
                        countCorrectAnswers++;
                        break;
                    case 1:
                        check = false;
                        buttonAnswer1.setBackgroundColor(Color.RED);
                        buttonAnswer2.setBackgroundColor(Color.rgb(4, 180, 4));
                        buttonAnswer3.setBackgroundColor(Color.WHITE);
                        break;
                    case 2:
                        check = false;
                        buttonAnswer1.setBackgroundColor(Color.RED);
                        buttonAnswer2.setBackgroundColor(Color.WHITE);
                        buttonAnswer3.setBackgroundColor(Color.rgb(4, 180, 4));
                        break;
                }
                break;
            case R.id.buttonAnswer2:
                switch (correctAnswer) {
                    case 0:
                        check = false;
                        buttonAnswer1.setBackgroundColor(Color.rgb(4, 180, 4));
                        buttonAnswer2.setBackgroundColor(Color.RED);
                        buttonAnswer3.setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        buttonAnswer1.setBackgroundColor(Color.WHITE);
                        buttonAnswer2.setBackgroundColor(Color.rgb(4, 180, 4));
                        buttonAnswer3.setBackgroundColor(Color.WHITE);
                        countCorrectAnswers++;
                        break;
                    case 2:
                        check = false;
                        buttonAnswer1.setBackgroundColor(Color.WHITE);
                        buttonAnswer2.setBackgroundColor(Color.RED);
                        buttonAnswer3.setBackgroundColor(Color.rgb(4, 180, 4));
                        break;
                }
                break;
            case R.id.buttonAnswer3:
                switch (correctAnswer) {
                    case 0:
                        check = false;
                        buttonAnswer1.setBackgroundColor(Color.rgb(4, 180, 4));
                        buttonAnswer2.setBackgroundColor(Color.WHITE);
                        buttonAnswer3.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        check = false;
                        buttonAnswer1.setBackgroundColor(Color.WHITE);
                        buttonAnswer2.setBackgroundColor(Color.rgb(4, 180, 4));
                        buttonAnswer3.setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        buttonAnswer1.setBackgroundColor(Color.WHITE);
                        buttonAnswer2.setBackgroundColor(Color.WHITE);
                        buttonAnswer3.setBackgroundColor(Color.rgb(4, 180, 4));
                        countCorrectAnswers++;
                        break;
                }
                break;
        }
        if (!check && currentWord.getKnown()) viewModel.setKnown(currentWord);

        buttonAnswer1.setEnabled(false);
        buttonAnswer2.setEnabled(false);
        buttonAnswer3.setEnabled(false);

        if (item != wordsChoice.size() - 1)
            buttonContinue.setVisibility(View.VISIBLE);
        else {
            buttonContinue.setVisibility(View.GONE);
            buttonFinish.setVisibility(View.VISIBLE);
        }

        setText();
    }

    private int getRandInt(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    private void getViewWord() {
        ArrayList<Word> list = new ArrayList<>();
        list.addAll(wordsKnown);
        list.addAll(wordsNoKnown);

        textRussian.setText(currentWord.getRussian());

        int j, k;
        switch (correctAnswer) {
            case 0:
                buttonAnswer1.setText(currentWord.getItalian());
                do {
                    j = getRandInt(list.size());
                } while (list.get(j).getItalian().equals(currentWord.getItalian()));
                buttonAnswer2.setText(list.get(j).getItalian());
                do {
                    k = getRandInt(list.size());
                } while (list.get(k).getItalian().equals(currentWord.getItalian()) || list.get(k).getItalian().equals(list.get(j).getItalian()));
                buttonAnswer3.setText(list.get(k).getItalian());
                break;
            case 1:
                buttonAnswer2.setText(currentWord.getItalian());
                do {
                    j = getRandInt(list.size());
                } while (list.get(j).getItalian().equals(currentWord.getItalian()));
                buttonAnswer1.setText(list.get(j).getItalian());
                do {
                    k = getRandInt(list.size());
                } while (list.get(k).getItalian().equals(currentWord.getItalian()) || list.get(k).getItalian().equals(list.get(j).getItalian()));
                buttonAnswer3.setText(list.get(k).getItalian());
                break;
            case 2:
                buttonAnswer3.setText(currentWord.getItalian());
                do {
                    j = getRandInt(list.size());
                } while (list.get(j).getItalian().equals(currentWord.getItalian()));
                buttonAnswer1.setText(list.get(j).getItalian());
                do {
                    k = getRandInt(list.size());
                } while (list.get(k).getItalian().equals(currentWord.getItalian()) || list.get(k).getItalian().equals(list.get(j).getItalian()));
                buttonAnswer2.setText(list.get(k).getItalian());
                break;
        }

        buttonAnswer1.setBackgroundColor(Color.MAGENTA);
        buttonAnswer1.setEnabled(true);

        buttonAnswer2.setBackgroundColor(Color.MAGENTA);
        buttonAnswer2.setEnabled(true);

        buttonAnswer3.setBackgroundColor(Color.MAGENTA);
        buttonAnswer3.setEnabled(true);

        buttonContinue.setVisibility(View.INVISIBLE);
        buttonFinish.setVisibility(View.GONE);

        if (currentWord.getImage() == 0) image.setVisibility(View.GONE);
        else {
            image.setImageResource(currentWord.getImage());
            image.setVisibility(View.VISIBLE);
        }
    }

    public void start() {
        wordsKnown = viewModel.selectAllKnown();
        wordsNoKnown = viewModel.selectAllNoKnown();

        ArrayList<Word> words = new ArrayList<>();
        if (wordsKnown.size() + wordsNoKnown.size() > countWordsChoice) {
            if (wordsKnown.size() > 4 && wordsNoKnown.size() > 3) {
                for (int i = 0; i < 4; ) {
                    int j = getRandInt(wordsKnown.size());
                    if (!words.contains(wordsKnown.get(j))) {
                        words.add(wordsKnown.get(j));
                        i++;
                    }
                }
                for (int i = 0; i < 3; ) {
                    int j = getRandInt(wordsNoKnown.size());
                    if (!words.contains(wordsNoKnown.get(j))) {
                        words.add(wordsNoKnown.get(j));
                        i++;
                    }
                }
            } else {
                if (wordsKnown.size() > 4) {
                    if (wordsNoKnown.size() > 0) words.addAll(wordsNoKnown);
                    for (int i = words.size(); i < countWordsChoice; ) {
                        int j = getRandInt(wordsKnown.size());
                        if (!words.contains(wordsKnown.get(j))) {
                            words.add(wordsKnown.get(j));
                            i++;
                        }
                    }
                } else {
                    if (wordsKnown.size() > 0) words.addAll(wordsKnown);
                    for (int i = words.size(); i < countWordsChoice; ) {
                        int j = getRandInt(wordsNoKnown.size());
                        if (!words.contains(wordsNoKnown.get(j))) {
                            words.add(wordsNoKnown.get(j));
                            i++;
                        }
                    }
                }
            }
        } else {
            words.addAll(wordsKnown);
            words.addAll(wordsNoKnown);
            countWordsChoice = words.size();
        }

        wordsChoice = new ArrayList<>();
        for (int i = 0; i < countWordsChoice; ) {
            int j = getRandInt(words.size());
            if (!wordsChoice.contains(words.get(j))) {
                wordsChoice.add(words.get(j));
                i++;
            }
        }

        countCorrectAnswers = 0;

        item = 0;
        currentWord = wordsChoice.get(item);

        correctAnswer = getRandInt(3);

        getViewWord();
        setText();

        layoutStart.setVisibility(View.INVISIBLE);

        layoutProcess.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    public void setText() {
        textCount.setText("Количество правильных ответов: " + countCorrectAnswers + " / " + countWordsChoice);
    }
}