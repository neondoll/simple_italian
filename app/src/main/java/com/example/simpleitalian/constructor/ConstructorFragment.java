package com.example.simpleitalian.constructor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.addnewword.AddNewWordFragment;
import com.example.simpleitalian.databinding.FragmentConstructorBinding;

import java.util.ArrayList;
import java.util.Random;

public class ConstructorFragment extends Fragment {
    private ArrayList<Button> buttons;
    private ArrayList<Word> list;
    private Button buttonClear, buttonVerify;
    private ConstructorViewModel constructorViewModel;
    private FragmentConstructorBinding binding;
    private TextView textRussian, textResult, editText;
    private int item;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentConstructorBinding.inflate(inflater, container, false);

        constructorViewModel = new ViewModelProvider(this).get(ConstructorViewModel.class);

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
        buttonClear = binding.buttonClear;
        buttonVerify = binding.buttonVerify;
        editText = binding.editText;
        textRussian = binding.textRussian;
        textResult = binding.textResult;

        /*ArrayList<Integer> word = new ArrayList<>();
        boolean check;
        String italian = list.get(item).getItalian();
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

        }*/
        String italian = list.get(item).getItalian();
        for (int i = 0; i < italian.length(); i++) {
            int finalI = i;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writeLetter(buttons.get(finalI));
                    buttons.get(finalI).setVisibility(View.INVISIBLE);
                }
            });
        }
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                for (int i = 0; i < italian.length(); i++) {
                    buttons.get(i).setVisibility(View.VISIBLE);
                }
            }
        });
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(italian)) {
                    textResult.setText("Успешно!");
                } else {
                    textResult.setText("Ошибка! Правильный ответ: " + italian);
                }
                buttonClear.setVisibility(View.GONE);
                buttonVerify.setVisibility(View.GONE);
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

    private void writeLetter(View view) {
        Button button = (Button) view;
        String text = editText.getText().toString();
        editText.setText(text + button.getText());
    }

    private void getViewWord(int item) {
        textRussian.setText(list.get(item).getRussian());
        editText.setText("");

        ArrayList<Integer> word = new ArrayList<>();
        boolean check;
        String italian = list.get(item).getItalian();
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
}
