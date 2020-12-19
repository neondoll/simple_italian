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

import com.example.simpleitalian.R;
import com.example.simpleitalian.Word;
import com.example.simpleitalian.databinding.FragmentLearnWordBinding;

import java.util.ArrayList;
import java.util.Random;

public class LearnWordFragment extends Fragment {
    private ArrayList<Word> list;
    private Button buttonKnow, buttonNoKnow, buttonSpeaker;
    private FragmentLearnWordBinding binding;
    private LearnWordViewModel learnWordViewModel;
    private ImageView image;
    private TextView textItalian, textTranscription, textRussian;
    private int item, sizeText = 24;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        learnWordViewModel = new ViewModelProvider(this).get(LearnWordViewModel.class);

        binding = FragmentLearnWordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonKnow = binding.buttonKnow;
        buttonNoKnow = binding.buttonNoKnow;
        buttonSpeaker = binding.buttonSpeaker;
        image = binding.imageView;
        textItalian = binding.textItalian;
        textItalian.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeText);
        textRussian = binding.textRussian;
        textRussian.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeText);
        textTranscription = binding.textTranscription;
        textTranscription.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeText);

        buttonKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(item).setKnown(true);
                item = getRandInt(list.size());
                getViewWord(item);
            }
        });
        buttonNoKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(item).setKnown(false);
                item = getRandInt(list.size());
                getViewWord(item);
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
        textItalian.setText(list.get(i).getItalian());
        textTranscription.setText(list.get(i).getTranscription());
        textRussian.setText(list.get(i).getRussian());
        if (list.get(i).getSpeech().equals("")) buttonSpeaker.setVisibility(View.GONE);
        else buttonSpeaker.setVisibility(View.VISIBLE);
        if (list.get(i).getImage() == 0) image.setVisibility(View.GONE);
        else {
            image.setImageResource(list.get(i).getImage());
            image.setVisibility(View.VISIBLE);
        }
    }
}
