package com.breaktime.englishwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Words extends AppCompatActivity {
    String PREFS_FILE = "wordsSave";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        getSupportActionBar().hide();
        new HideNavigation(getWindow());
    }

    public void saveData(String line){
        SaveData saveData = new SaveData(getSharedPreferences(PREFS_FILE, MODE_PRIVATE));
        saveData.setText(line);
    }

    public void unit_1_Topical_Materials(View view){
        String line = getString(R.string.unit_1_Topical_Materials) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_2_Topical_Materials(View view){
        String line = getString(R.string.unit_2_Topical_Materials) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_4_Topical_Materials(View view){
        String line = getString(R.string.unit_4_Topical_Materials) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_1_Information_Technologies(View view){
        String line = getString(R.string.unit_1_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_2_Information_Technologies(View view){
        String line = getString(R.string.unit_2_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_3_Information_Technologies(View view){
        String line = getString(R.string.unit_3_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }
    public void unit_4_Information_Technologies(View view){
        String line = getString(R.string.unit_4_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }
    public void unit_5_Information_Technologies(View view){
        String line = getString(R.string.unit_5_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_6_Information_Technologies(View view){
        String line = getString(R.string.unit_6_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_7_Information_Technologies(View view){
        String line = getString(R.string.unit_7_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

    public void unit_8_Information_Technologies(View view){
        String line = getString(R.string.unit_8_Information_Technologies) +"";
        saveData(line);
        Intent intent = new Intent(this, LearnWords.class);
        startActivity(intent);
    }

}
