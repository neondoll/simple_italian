package com.example.simpleitalian.learnword;

import android.graphics.BitmapFactory;
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
import com.example.simpleitalian.databinding.FragmentLearnWordBinding;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class LearnWordFragment extends Fragment {
    private ArrayList<Word> list;
    private Button buttonKnow, buttonNoKnow;
    private FragmentLearnWordBinding binding;
    private LearnWordViewModel learnWordViewModel;
    private int i;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        list = new ArrayList<>();
        //list.add(new Word(1, "l'uomo", "[л уОмо]", "мужчина", "", ""));
        //list.add(new Word(2, "la donna", "[ла дОнна]", "женщина", "", ""));
        //list.add(new Word(3, "il ragazzo", "[иль рагАццо]", "мальчик", "", ""));
        //list.add(new Word(4, "la ragazzo", "[ла рагАцца]", "девочка", "", ""));
        //list.add(new Word(5, "l'amico", "[л амИко]", "друг", "", ""));
        list.add(new Word(6, "la famiglia", "[ла фамИлья]", "семья", "", "image1.jpg"));
        //list.add(new Word(7, "i genitori", "[и дженитОри]", "родители", "", ""));
        //list.add(new Word(8, "il bambino", "[иль бамибИно]", "ребёнок", "", ""));
        //list.add(new Word(9, "il bimbo", "[иль бИмбо]", "малыш", "", ""));
        list.add(new Word(10, "il papà", "[иль папА]", "папа", "", "image4.jpg"));
        list.add(new Word(11, "la mamma", "[ла маммА]", "мама", "", "image5.jpg"));
        list.add(new Word(12, "il fratello", "[иль фратЕлло]", "брат", "", "image2.jpg"));
        list.add(new Word(13, "la sorella", "[ла сорЕлла]", "сестра", "", "image3.jpg"));
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

        i = getRandInt(list.size());

        learnWordViewModel = new ViewModelProvider(this).get(LearnWordViewModel.class);

        binding = FragmentLearnWordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonKnow = binding.buttonKnow;
        buttonNoKnow = binding.buttonNoKnow;

        buttonKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setKnown(true);
                i = getRandInt(list.size());
                getViewWord(i);
            }
        });
        buttonNoKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setKnown(false);
                i = getRandInt(list.size());
                getViewWord(i);
            }
        });

        getViewWord(i);
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
        binding.textItalian.setText(list.get(i).getItalian());
        binding.textTranscription.setText(list.get(i).getTranscription());
        binding.textRussian.setText(list.get(i).getRussian());
        if (list.get(i).getSpeech().equals("")) binding.buttonSpeaker.setVisibility(View.GONE);
        else binding.buttonSpeaker.setVisibility(View.VISIBLE);
        if (list.get(i).getImage().equals("")) binding.imageView.setVisibility(View.GONE);
        else {
            /*binding.imageView.setVisibility(View.VISIBLE);
            try {
                binding.imageView.setImageBitmap(BitmapFactory.decodeStream(new URL("https://sun9-34.userapi.com/impf/76ko8SxvO9p0jkpfiUtrNLJDtcW3HsyyIbPx1w/v_nxDVCpT9M.jpg?size=1080x1080&quality=96&proxy=1&sign=a79039487e92147c32e95cb3e4c9d44f&type=album").openConnection().getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
