package com.example.simpleitalian;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.simpleitalian.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startDB(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        /*binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Передача каждого идентификатора меню как набора идентификаторов, поскольку
        // каждое меню следует рассматривать как пункты назначения верхнего уровня.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_add_new_word, R.id.nav_choice, R.id.nav_constructor, R.id.nav_home, R.id.nav_learn_word, R.id.nav_my_word)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Раздуть меню; это добавляет элементы на панель действий, если она присутствует.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private void startDB(Context context) {
        DBWords DBConnector = new DBWords(context);
        if (!DBConnector.isInDatabase("la famiglia", "семья")) {
            DBConnector.insert("la famiglia", "семья", "[ла фамИлья]", R.drawable.image1, 0);
        }
        if (!DBConnector.isInDatabase("il papà", "папа")) {
            DBConnector.insert("il papà", "папа", "[иль папА]", R.drawable.image4, 0);
        }
        if (!DBConnector.isInDatabase("la mamma", "мама")) {
            DBConnector.insert("la mamma", "мама", "[ла маммА]", R.drawable.image5, 0);
        }
        if (!DBConnector.isInDatabase("il fratello", "брат")) {
            DBConnector.insert("il fratello", "брат", "[иль фратЕлло]", R.drawable.image2, 0);
        }
        if (!DBConnector.isInDatabase("la sorella", "сестра")) {
            DBConnector.insert("la sorella", "сестра", "[ла сорЕлла]", R.drawable.image3, 0);
        }
        if (!DBConnector.isInDatabase("l'uomo", "мужчина")) {
            DBConnector.insert("l'uomo", "мужчина", "[л уОмо]", R.drawable.image6, 0);
        }
        if (!DBConnector.isInDatabase("la donna", "женщина")) {
            DBConnector.insert("la donna", "женщина", "[ла дОнна]", R.drawable.image7, 0);
        }
        if (!DBConnector.isInDatabase("il ragazzo", "мальчик")) {
            DBConnector.insert("il ragazzo", "мальчик", "[иль рагАццо]", R.drawable.image8, 0);
        }
        if (!DBConnector.isInDatabase("la ragazzo", "девочка")) {
            DBConnector.insert("la ragazzo", "девочка", "[ла рагАцца]", R.drawable.image9, 0);
        }
        if (!DBConnector.isInDatabase("l'amico", "друг")) {
            DBConnector.insert("l'amico", "друг", "[л амИко]", R.drawable.image10, 0);
        }/**/
        ArrayList<Word> words = DBConnector.selectAll();
        for (int i = 0; i < words.size(); i++) words.get(i).print();
        //DBConnector.deleteAll();
    }
}