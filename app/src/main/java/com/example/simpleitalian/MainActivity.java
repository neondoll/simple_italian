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
    public DBWords DBConnector;

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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_add_new_word, R.id.nav_choice, R.id.nav_constructor, R.id.nav_learn_word, R.id.nav_my_word)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private void startDB(Context context) {
        DBConnector = new DBWords(context);
        if (!isWordInDatabase("la famiglia", "семья"))
            DBConnector.insert("la famiglia", "семья", "[ла фамИлья]", R.drawable.image1, 0);
        if (!isWordInDatabase("il papà", "папа"))
            DBConnector.insert("il papà", "папа", "[иль папА]", R.drawable.image4, 0);
        if (!isWordInDatabase("la mamma", "мама"))
            DBConnector.insert("la mamma", "мама", "[ла маммА]", R.drawable.image5, 0);
        if (!isWordInDatabase("il fratello", "брат"))
            DBConnector.insert("il fratello", "брат", "[иль фратЕлло]", R.drawable.image2, 0);
        if (!isWordInDatabase("la sorella", "сестра"))
            DBConnector.insert("la sorella", "сестра", "[ла сорЕлла]", R.drawable.image3, 0);
        ArrayList<Word> word = DBConnector.selectAll();
        for (int i = 0; i < word.size(); i++)
            System.out.println(word.get(i).getId() + " - " + word.get(i).getItalian());
        //DBConnector.deleteAll();
    }

    private boolean isWordInDatabase(String italian, String russian) {
        ArrayList<Word> words = DBConnector.selectAll(italian);
        if (words.size() > 0) {
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getRussian().equals(russian)) {
                    return true;
                }
            }
        }
        return false;
    }
}