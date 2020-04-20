package com.breaktime.englishwords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String PREFS_FILE = "wordsSave";
    SaveData saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setImage();
        saveData = new SaveData(getSharedPreferences(PREFS_FILE, MODE_PRIVATE));
        getSupportActionBar().hide();
        new HideNavigation(getWindow());
    }

    public void setDayNightMode(View view) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED || AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            saveData.setDayNightMode();
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            saveData.setDayNightMode();
        }
        setContentView(R.layout.activity_main);
        setImage();
    }

    public void setImage(){
        ImageButton imageButton = findViewById(R.id.dayNightImage);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED || AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            imageButton.setBackground(getDrawable(R.drawable.moon));
        }
        else {
            imageButton.setBackground(getDrawable(R.drawable.sun));
        }
    }

    public void words(View view) {
        Intent intent = new Intent(this, Words.class);
        startActivity(intent);
    }

    public void abbreviations(View view) {
        saveData.setText(getText(R.string.abbreviations) + "");
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void completeRepetition(View view) {
        SaveData saveData = new SaveData(getSharedPreferences(PREFS_FILE, MODE_PRIVATE));
        String line = String.valueOf(getText(R.string.unit_1_Topical_Materials));
        line += String.valueOf(getText(R.string.unit_2_Topical_Materials));
        line += String.valueOf(getText(R.string.unit_4_Topical_Materials));
        line += String.valueOf(getText(R.string.unit_1_Information_Technologies));
        line += String.valueOf(getText(R.string.unit_2_Information_Technologies));
        line += String.valueOf(getText(R.string.unit_3_Information_Technologies));
        line += String.valueOf(getText(R.string.unit_4_Information_Technologies));
        line += String.valueOf(getText(R.string.unit_5_Information_Technologies));
        line += String.valueOf(getText(R.string.unit_6_Information_Technologies));
//        line += String.valueOf(getText(R.string.unit_7_Information_Technologies));
//        line += String.valueOf(getText(R.string.unit_8_Information_Technologies));
        saveData.setText(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void lastMistakes(View view) {
        String line = saveData.getMistakes();
        if (line.length() != 0) {
            saveData.setText(line);
            Intent intent = new Intent(this, LearnWords.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "you don't have errors", Toast.LENGTH_SHORT).show();
    }

    public void exit(View view) {
        finish();
    }


}
