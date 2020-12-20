package com.example.simpleitalian.choice;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleitalian.DBWords;
import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentChoiceBinding;

import java.util.ArrayList;
import java.util.Random;

public class ChoiceFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Word> list;
    private ArrayList<Word> listChoice;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonContinue;
    private Button buttonFinish;
    private Button buttonRestart;
    private DBWords DBConnector;
    private ChoiceViewModel choiceViewModel;
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
    private int countWords = 3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBConnector = new DBWords(getActivity());

        binding = FragmentChoiceBinding.inflate(inflater, container, false);

        choiceViewModel = new ViewModelProvider(this).get(ChoiceViewModel.class);

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
                currentWord = listChoice.get(item);

                correctAnswer = getRandInt(3);
                //System.out.println(correctAnswer);

                getViewWord();

                buttonAnswer1.setBackgroundColor(Color.MAGENTA);
                buttonAnswer2.setBackgroundColor(Color.MAGENTA);
                buttonAnswer3.setBackgroundColor(Color.MAGENTA);

                buttonContinue.setVisibility(View.GONE);
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
        list.get(item).setKnown(check);

        buttonAnswer1.setEnabled(false);
        buttonAnswer2.setEnabled(false);
        buttonAnswer3.setEnabled(false);

        if (check) {
            if (countCorrectAnswers != listChoice.size())
                buttonContinue.setVisibility(View.VISIBLE);
            else buttonFinish.setVisibility(View.VISIBLE);
        } else buttonRestart.setVisibility(View.VISIBLE);


        textCount.setText("Количество правильных ответов: " + countCorrectAnswers);
    }

    private int getRandInt(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    private void getViewWord() {
        textRussian.setText(currentWord.getRussian());

        int j, k;
        switch (correctAnswer) {
            case 0:
                buttonAnswer1.setText(currentWord.getItalian());
                do {
                    j = getRandInt(list.size());
                } while (list.get(j).getId() == currentWord.getId());
                buttonAnswer2.setText(list.get(j).getItalian());
                do {
                    k = getRandInt(list.size());
                } while (list.get(k).getId() == currentWord.getId() || list.get(k).getId() == list.get(j).getId());
                buttonAnswer3.setText(list.get(k).getItalian());
                break;
            case 1:
                buttonAnswer2.setText(currentWord.getItalian());
                do {
                    j = getRandInt(list.size());
                } while (list.get(j).getId() == currentWord.getId());
                buttonAnswer1.setText(list.get(j).getItalian());
                do {
                    k = getRandInt(list.size());
                } while (list.get(k).getId() == currentWord.getId() || list.get(k).getId() == list.get(j).getId());
                buttonAnswer3.setText(list.get(k).getItalian());
                break;
            case 2:
                buttonAnswer3.setText(currentWord.getItalian());
                do {
                    j = getRandInt(list.size());
                } while (list.get(j).getId() == currentWord.getId());
                buttonAnswer1.setText(list.get(j).getItalian());
                do {
                    k = getRandInt(list.size());
                } while (list.get(k).getId() == currentWord.getId() || list.get(k).getId() == list.get(j).getId());
                buttonAnswer2.setText(list.get(k).getItalian());
                break;
        }
        buttonAnswer1.setBackgroundColor(Color.MAGENTA);
        buttonAnswer1.setEnabled(true);
        buttonAnswer2.setBackgroundColor(Color.MAGENTA);
        buttonAnswer2.setEnabled(true);
        buttonAnswer3.setBackgroundColor(Color.MAGENTA);
        buttonAnswer3.setEnabled(true);

        buttonContinue.setVisibility(View.GONE);
        buttonFinish.setVisibility(View.GONE);

        if (currentWord.getImage() == 0) image.setVisibility(View.GONE);
        else {
            image.setImageResource(currentWord.getImage());
            image.setVisibility(View.VISIBLE);
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

        listChoice = new ArrayList<>();
        for (int i = 0; i < countWords; ) {
            int j = getRandInt(list.size());
            if (listChoice.indexOf(list.get(j)) == -1) {
                listChoice.add(list.get(j));
                i++;
            }
        }

        countCorrectAnswers = 0;

        item = 0;
        currentWord = listChoice.get(item);

        correctAnswer = getRandInt(3);
        //System.out.println(correctAnswer);

        getViewWord();

        textCount.setText("Количество правильных ответов: " + countCorrectAnswers);

        layoutStart.setVisibility(View.INVISIBLE);

        layoutProcess.setVisibility(View.VISIBLE);

        buttonRestart.setVisibility(View.GONE);
    }
}