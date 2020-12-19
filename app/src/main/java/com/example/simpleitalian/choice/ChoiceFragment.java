package com.example.simpleitalian.choice;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentChoiceBinding;

import java.util.ArrayList;
import java.util.Random;

public class ChoiceFragment extends Fragment {
    private ArrayList<Word> list;
    private Button buttonAnswer1, buttonAnswer2, buttonAnswer3;
    private ChoiceViewModel choiceViewModel;
    private FragmentChoiceBinding binding;
    private int item, correctAnswer;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChoiceBinding.inflate(inflater, container, false);

        choiceViewModel = new ViewModelProvider(this).get(ChoiceViewModel.class);

        correctAnswer = getRandInt(3);
        System.out.println(correctAnswer);

        list = new ArrayList<>();
        //list.add(new Word(1, "l'uomo", "[л уОмо]", "мужчина", "", ""));
        //list.add(new Word(2, "la donna", "[ла дОнна]", "женщина", "", ""));
        //list.add(new Word(3, "il ragazzo", "[иль рагАццо]", "мальчик", "", ""));
        //list.add(new Word(4, "la ragazzo", "[ла рагАцца]", "девочка", "", ""));
        //list.add(new Word(5, "l'amico", "[л амИко]", "друг", "", ""));
        list.add(new Word(6, "la famiglia", "[ла фамИлья]", "семья", "", R.drawable.image1));
        //list.add(new Word(7, "i genitori", "[и дженитОри]", "родители", "", ""));
        //list.add(new Word(8, "il bambino", "[иль бамибИно]", "ребёнок", "", ""));
        //list.add(new Word(9, "il bimbo", "[иль бИмбо]", "малыш", "", ""));
        list.add(new Word(10, "il papà", "[иль папА]", "папа", "", R.drawable.image4));
        list.add(new Word(11, "la mamma", "[ла маммА]", "мама", "", R.drawable.image5));
        list.add(new Word(12, "il fratello", "[иль фратЕлло]", "брат", "", R.drawable.image2));
        list.add(new Word(13, "la sorella", "[ла сорЕлла]", "сестра", "", R.drawable.image3));
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

        item = getRandInt(list.size());

        View root = binding.getRoot();

        buttonAnswer1 = binding.buttonAnswer1;
        buttonAnswer2 = binding.buttonAnswer2;
        buttonAnswer3 = binding.buttonAnswer3;

        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (correctAnswer) {
                    case 0:
                        list.get(item).setKnown(true);
                        buttonAnswer1.setBackgroundColor(Color.GREEN);
                        break;
                    case 1:
                        list.get(item).setKnown(false);
                        buttonAnswer1.setBackgroundColor(Color.RED);
                        buttonAnswer2.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        list.get(item).setKnown(false);
                        buttonAnswer1.setBackgroundColor(Color.RED);
                        buttonAnswer3.setBackgroundColor(Color.GREEN);
                        break;
                }
            }
        });
        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (correctAnswer) {
                    case 0:
                        list.get(item).setKnown(false);
                        buttonAnswer1.setBackgroundColor(Color.GREEN);
                        buttonAnswer2.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        list.get(item).setKnown(true);
                        buttonAnswer2.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        list.get(item).setKnown(false);
                        buttonAnswer2.setBackgroundColor(Color.RED);
                        buttonAnswer3.setBackgroundColor(Color.GREEN);
                        break;
                }
            }
        });
        buttonAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (correctAnswer) {
                    case 0:
                        list.get(item).setKnown(false);
                        buttonAnswer1.setBackgroundColor(Color.GREEN);
                        buttonAnswer3.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        list.get(item).setKnown(false);
                        buttonAnswer2.setBackgroundColor(Color.GREEN);
                        buttonAnswer3.setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        list.get(item).setKnown(true);
                        buttonAnswer3.setBackgroundColor(Color.GREEN);
                        break;
                }
            }
        });

        getViewWord(item);

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

    private void getViewWord(int i) {
        binding.textRussian.setText(list.get(i).getRussian());
        int j, k;
        switch (correctAnswer) {
            case 0:
                binding.buttonAnswer1.setText(list.get(i).getItalian());
                j = getRandInt(list.size());
                while (j == i) j = getRandInt(list.size());
                binding.buttonAnswer2.setText(list.get(j).getItalian());
                k = getRandInt(list.size());
                while (k == i || k == j) k = getRandInt(list.size());
                binding.buttonAnswer3.setText(list.get(k).getItalian());
                break;
            case 1:
                binding.buttonAnswer2.setText(list.get(i).getItalian());
                j = getRandInt(list.size());
                while (j == i) j = getRandInt(list.size());
                binding.buttonAnswer1.setText(list.get(j).getItalian());
                k = getRandInt(list.size());
                while (k == i || k == j) k = getRandInt(list.size());
                binding.buttonAnswer3.setText(list.get(k).getItalian());
                break;
            case 2:
                binding.buttonAnswer3.setText(list.get(i).getItalian());
                j = getRandInt(list.size());
                while (j == i) j = getRandInt(list.size());
                binding.buttonAnswer1.setText(list.get(j).getItalian());
                k = getRandInt(list.size());
                while (k == i || k == j) k = getRandInt(list.size());
                binding.buttonAnswer2.setText(list.get(k).getItalian());
                break;
        }
        if (list.get(i).getImage() == 0) binding.imageView.setVisibility(View.GONE);
        else binding.imageView.setVisibility(View.VISIBLE);
    }
}
