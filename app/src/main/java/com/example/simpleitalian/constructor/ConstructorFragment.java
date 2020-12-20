package com.example.simpleitalian.constructor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleitalian.DBWords;
import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentConstructorBinding;

import java.util.ArrayList;
import java.util.Random;

public class ConstructorFragment extends Fragment {
    private ArrayList<Button> buttons;
    private ArrayList<Word> list;
    private ArrayList<Word> listConstructor;
    private Button buttonClear;
    private Button buttonContinue;
    private Button buttonFinish;
    private Button buttonRestart;
    private Button buttonVerify;
    private DBWords DBConnector;
    private ConstructorViewModel constructorViewModel;
    private FragmentConstructorBinding binding;
    private LinearLayout layoutProcess;
    private LinearLayout layoutStart;
    private TextView editText;
    private TextView textResult;
    private TextView textRussian;
    private Word currentWord;
    private int countCorrectAnswers;
    private int countWords = 3;
    private int item;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBConnector = new DBWords(getActivity());

        binding = FragmentConstructorBinding.inflate(inflater, container, false);

        constructorViewModel = new ViewModelProvider(this).get(ConstructorViewModel.class);

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
        buttons.add((Button) binding.button1);
        buttons.add((Button) binding.button2);
        buttons.add((Button) binding.button3);
        buttons.add((Button) binding.button4);
        buttons.add((Button) binding.button5);
        buttons.add((Button) binding.button6);
        buttons.add((Button) binding.button7);
        buttons.add((Button) binding.button8);
        buttons.add((Button) binding.button9);
        buttons.add((Button) binding.button10);
        buttons.add((Button) binding.button11);
        buttons.add((Button) binding.button12);
        buttons.add((Button) binding.button13);
        buttons.add((Button) binding.button14);
        buttons.add((Button) binding.button15);
        buttons.add((Button) binding.button16);
        buttons.add((Button) binding.button17);
        buttons.add((Button) binding.button18);
        buttons.add((Button) binding.button19);
        buttons.add((Button) binding.button20);
        buttons.add((Button) binding.button21);
        buttons.add((Button) binding.button22);
        buttons.add((Button) binding.button23);
        buttons.add((Button) binding.button24);
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
                currentWord = listConstructor.get(item);

                getViewWord();

                for (int i = 0; i < buttons.size(); i++) buttons.get(i).setEnabled(true);
                buttonContinue.setVisibility(View.GONE);
                buttonClear.setVisibility(View.VISIBLE);
                buttonVerify.setVisibility(View.VISIBLE);
                textResult.setText("");
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

        buttonRestart = binding.buttonRestart;
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        buttonVerify = binding.buttonVerify;
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < buttons.size(); i++) buttons.get(i).setEnabled(false);
                if (editText.getText().toString().equals(currentWord.getItalian())) {
                    textResult.setText("Успешно!");
                    countCorrectAnswers++;
                    if (countCorrectAnswers != listConstructor.size())
                        buttonContinue.setVisibility(View.VISIBLE);
                    else buttonFinish.setVisibility(View.VISIBLE);
                } else {
                    textResult.setText("Ошибка! Правильный ответ: " + currentWord.getItalian());
                    buttonRestart.setVisibility(View.VISIBLE);
                }
                buttonClear.setVisibility(View.GONE);
                buttonVerify.setVisibility(View.GONE);
            }
        });

        editText = binding.editText;

        textRussian = binding.textRussian;

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

    private void writeLetter(View view) {
        Button button = (Button) view;
        String text = editText.getText().toString();
        editText.setText(text + button.getText());
    }

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
                if ((int) (word.size() / 6) < (int) (i / 6))
                    buttons.get(i).setVisibility(View.GONE);
                else buttons.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    public void start() {
        list = DBConnector.selectAll();
        //list = new ArrayList<>();
        //list.add(new Word(1, "l'uomo", "[л уОмо]", "мужчина", "", ""));
        //list.add(new Word(2, "la donna", "[ла дОнна]", "женщина", "", ""));
        //list.add(new Word(3, "il ragazzo", "[иль рагАццо]", "мальчик", "", ""));
        //list.add(new Word(4, "la ragazzo", "[ла рагАцца]", "девочка", "", ""));
        //list.add(new Word(5, "l'amico", "[л амИко]", "друг", "", ""));
        //list.add(new Word(6, "la famiglia", "[ла фамИлья]", "семья", 0, R.drawable.image1));
        //list.add(new Word(7, "i genitori", "[и дженитОри]", "родители", "", ""));
        //list.add(new Word(8, "il bambino", "[иль бамибИно]", "ребёнок", "", ""));
        //list.add(new Word(9, "il bimbo", "[иль бИмбо]", "малыш", "", ""));
        //list.add(new Word(10, "il papà", "[иль папА]", "папа", 0, R.drawable.image4));
        //list.add(new Word(11, "la mamma", "[ла маммА]", "мама", 0, R.drawable.image5));
        //list.add(new Word(12, "il fratello", "[иль фратЕлло]", "брат", 0, R.drawable.image2));
        //list.add(new Word(13, "la sorella", "[ла сорЕлла]", "сестра", 0, R.drawable.image3));
        /*list.add(new Word(14, "il figlio", "[иль фИльо]", "сын", "", ""));
        list.add(new Word(15, "la figlia", "[ла фИлья]", "дочь", "", ""));
        list.add(new Word(16, "la nonna", "[ла нОнна]", "бабушка", "", ""));
        list.add(new Word(17, "il nonno", "[иль нОнно]", "дедушка", "", ""));
        list.add(new Word(18, "lo zio", "[ло дзИо]", "дядя", "", ""));
        list.add(new Word(19, "la zia", "[ла дзИа]", "тётя", "", ""));
        list.add(new Word(20, "la moglie", "[ла мОльэ]", "жена", "", ""));
        list.add(new Word(21, "il marito", "[иль марИто]", "муж", "", ""));
        list.add(new Word(22, "il nipote", "[иль нипОтэ]", "внук, племянник", "", ""));
        list.add(new Word(23, "la nipote", "[ла нипОтэ]", "внучка, племянник", "", ""));
        list.add(new Word(24, "il cugino", "[иль куджИно]", "двоюродный брат", "", ""));
        list.add(new Word(25, "la сugina", "[ла куджИна]", "двоюродная сестра", "", ""));*/

        listConstructor = new ArrayList<>();
        for (int i = 0; i < countWords; ) {
            int j = getRandInt(list.size());
            if (listConstructor.indexOf(list.get(j)) == -1) {
                listConstructor.add(list.get(j));
                i++;
            }
        }

        countCorrectAnswers = 0;

        item = 0;
        currentWord = listConstructor.get(item);

        getViewWord();

        //textCount.setText("Количество правильных ответов: " + countCorrectAnswers);

        layoutStart.setVisibility(View.INVISIBLE);

        layoutProcess.setVisibility(View.VISIBLE);

        for (int i = 0; i < buttons.size(); i++) buttons.get(i).setEnabled(true);
        buttonClear.setVisibility(View.VISIBLE);
        buttonFinish.setVisibility(View.GONE);
        buttonRestart.setVisibility(View.GONE);
        buttonVerify.setVisibility(View.VISIBLE);

        textResult.setText("");
    }
}
