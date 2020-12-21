package com.example.simpleitalian.learnword;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.simpleitalian.DBWords;
import com.example.simpleitalian.MainActivity;
import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentLearnWordBinding;

import java.util.ArrayList;
import java.util.Random;

public class LearnWordFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Word> list;
    private Button buttonKnow, buttonNoKnow, buttonSpeaker;
    private FragmentLearnWordBinding binding;
    private LearnWordViewModel learnWordViewModel;
    private ImageView image;
    private TextView textItalian, textTranscription, textRussian;
    private int item, sizeText = 24, unit = TypedValue.COMPLEX_UNIT_SP;
    private Word currentWord;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBWords DBConnector = new DBWords(getActivity());

        binding = FragmentLearnWordBinding.inflate(inflater, container, false);
        learnWordViewModel = new ViewModelProvider(this).get(LearnWordViewModel.class);

        list = new ArrayList<>();
        list.add(new Word(0, "", "", "", 0, 0, false));
        list.addAll(DBConnector.selectAll());
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

        item = getRandInt(list.size() - 1) + 1;
        currentWord = list.get(item);

        View root = binding.getRoot();

        buttonKnow = binding.buttonKnow;
        buttonNoKnow = binding.buttonNoKnow;
        buttonSpeaker = binding.buttonSpeaker;
        image = binding.imageView;
        textItalian = binding.textItalian;
        textItalian.setTextSize(unit, sizeText);
        textRussian = binding.textRussian;
        textRussian.setTextSize(unit, sizeText);
        textTranscription = binding.textTranscription;
        textTranscription.setTextSize(unit, sizeText);

        buttonKnow.setOnClickListener(this);
        buttonNoKnow.setOnClickListener(this);

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
        textTranscription.setText(currentWord.getTranscription());
        textRussian.setText(currentWord.getRussian());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_know:
                list.get(item).setKnown(true);
                list.remove(item);
                break;
            case R.id.button_no_know:
                list.get(item).setKnown(false);
                break;
        }
        item = getRandInt(list.size() - 1) + 1;
        currentWord = list.get(item);
        getViewWord();
    }
}
