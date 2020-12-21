package com.example.simpleitalian.constructor;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentConstructorBinding;

import java.util.ArrayList;
import java.util.Random;

public class ConstructorFragment extends Fragment {
    private ArrayList<Button> buttons;
    private ArrayList<Word> wordsConstructor;
    private Button buttonClear;
    private Button buttonContinue;
    private Button buttonFinish;
    private Button buttonVerify;
    private ConstructorViewModel viewModel;
    private FragmentConstructorBinding binding;
    private LinearLayout layoutProcess;
    private LinearLayout layoutStart;
    private TextView editText;
    private TextView textCount;
    private Button textResult;
    private TextView textRussian;
    private Word currentWord;
    private int countCorrectAnswers;
    private int countWordsConstructor = 7;
    private int item;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //constructorViewModel = new ViewModelProvider(this).get(ConstructorViewModel.class);
        viewModel = new ConstructorViewModel(getActivity());

        binding = FragmentConstructorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        layoutStart = binding.layoutStart;

        Button buttonStart = binding.buttonStart;
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        layoutProcess = binding.layoutProcess;

        buttons = new ArrayList<>();
        buttons.add(binding.button1);
        buttons.add(binding.button2);
        buttons.add(binding.button3);
        buttons.add(binding.button4);
        buttons.add(binding.button5);
        buttons.add(binding.button6);
        buttons.add(binding.button7);
        buttons.add(binding.button8);
        buttons.add(binding.button9);
        buttons.add(binding.button10);
        buttons.add(binding.button11);
        buttons.add(binding.button12);
        buttons.add(binding.button13);
        buttons.add(binding.button14);
        buttons.add(binding.button15);
        buttons.add(binding.button16);
        buttons.add(binding.button17);
        buttons.add(binding.button18);
        buttons.add(binding.button19);
        buttons.add(binding.button20);
        buttons.add(binding.button21);
        buttons.add(binding.button22);
        buttons.add(binding.button23);
        buttons.add(binding.button24);
        for (int i = 0; i < buttons.size(); i++) {
            int finalI = i;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writeLetter(buttons.get(finalI));
                    buttons.get(finalI).setVisibility(View.INVISIBLE);
                }
            });
        }

        buttonClear = binding.buttonClear;
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                for (int i = 0; i < currentWord.getItalian().length(); i++) {
                    buttons.get(i).setVisibility(View.VISIBLE);
                }
            }
        });

        buttonContinue = binding.buttonContinue;
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item++;
                currentWord = wordsConstructor.get(item);

                getViewWord();

                for (int i = 0; i < buttons.size(); i++) buttons.get(i).setEnabled(true);
                buttonContinue.setVisibility(View.GONE);
                buttonClear.setVisibility(View.VISIBLE);
                buttonVerify.setVisibility(View.VISIBLE);
                textResult.setVisibility(View.GONE);
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

        buttonVerify = binding.buttonVerify;
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                for (int i = 0; i < buttons.size(); i++) buttons.get(i).setEnabled(false);
                if (editText.getText().toString().equals(currentWord.getItalian())) {
                    textResult.setText("Успешно!");
                    textResult.setVisibility(View.VISIBLE);
                    textResult.setBackgroundColor(Color.rgb(4, 180, 4));
                    textResult.setTextColor(Color.WHITE);
                    countCorrectAnswers++;
                } else {
                    textResult.setText("Ошибка! Правильный ответ: " + currentWord.getItalian());
                    textResult.setVisibility(View.VISIBLE);
                    textResult.setBackgroundColor(Color.RED);
                    textResult.setTextColor(Color.WHITE);
                    viewModel.setKnown(currentWord);
                }

                setText();

                if (item != wordsConstructor.size() - 1)
                    buttonContinue.setVisibility(View.VISIBLE);
                else buttonFinish.setVisibility(View.VISIBLE);

                buttonClear.setVisibility(View.GONE);
                buttonVerify.setVisibility(View.GONE);
            }
        });

        editText = binding.editText;
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        textCount = binding.textCount;
        textCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        textRussian = binding.textRussian;
        textRussian.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        textResult = binding.textResult;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private int getRandInt(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    @SuppressLint("SetTextI18n")
    private void writeLetter(View view) {
        Button button = (Button) view;
        String text = editText.getText().toString();
        editText.setText(text + button.getText());
    }

    @SuppressLint("SetTextI18n")
    private void getViewWord() {
        textRussian.setText(currentWord.getRussian());

        editText.setText("");

        ArrayList<Integer> word = new ArrayList<>();
        boolean check;
        String italian = currentWord.getItalian();
        while (true) {
            check = true;
            int i = getRandInt(italian.length());
            for (int j = 0; j < word.size(); j++) {
                if (i == word.get(j)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                word.add(i);
                if (word.size() == italian.length()) break;
            }
        }

        for (int i = 0; i < buttons.size(); i++) {
            System.out.println(i);
            if (i < word.size()) {
                buttons.get(i).setText("" + italian.charAt(word.get(i)));
                buttons.get(i).setVisibility(View.VISIBLE);
            } else {
                if ((word.size() / 6) < (i / 6))
                    buttons.get(i).setVisibility(View.GONE);
                else buttons.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void start() {
        ArrayList<Word> wordsKnown = viewModel.selectAllKnown();
        ArrayList<Word> wordsNoKnown = viewModel.selectAllNoKnown();

        ArrayList<Word> words = new ArrayList<>();
        if (wordsKnown.size() + wordsNoKnown.size() > countWordsConstructor) {
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
                    for (int i = words.size(); i < countWordsConstructor; ) {
                        int j = getRandInt(wordsKnown.size());
                        if (!words.contains(wordsKnown.get(j))) {
                            words.add(wordsKnown.get(j));
                            i++;
                        }
                    }
                } else {
                    if (wordsKnown.size() > 0) words.addAll(wordsKnown);
                    for (int i = words.size(); i < countWordsConstructor; ) {
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
            countWordsConstructor = words.size();
        }

        wordsConstructor = new ArrayList<>();
        for (int i = 0; i < countWordsConstructor; ) {
            int j = getRandInt(words.size());
            if (!wordsConstructor.contains(words.get(j))) {
                wordsConstructor.add(words.get(j));
                i++;
            }
        }

        countCorrectAnswers = 0;

        item = 0;
        currentWord = wordsConstructor.get(item);

        getViewWord();
        setText();

        layoutStart.setVisibility(View.INVISIBLE);

        layoutProcess.setVisibility(View.VISIBLE);

        for (int i = 0; i < buttons.size(); i++) buttons.get(i).setEnabled(true);
        buttonClear.setVisibility(View.VISIBLE);
        buttonFinish.setVisibility(View.GONE);
        buttonVerify.setVisibility(View.VISIBLE);

        textResult.setText("");
    }

    @SuppressLint("SetTextI18n")
    public void setText() {
        textCount.setText("Количество правильных ответов: " + countCorrectAnswers + " / " + countWordsConstructor);
    }
}