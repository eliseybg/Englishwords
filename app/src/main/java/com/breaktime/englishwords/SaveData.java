package com.breaktime.englishwords;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class SaveData {

    SharedPreferences sharedPreferences;

    String PREF_WORDS = "words";
    String PREF_ERRORS = "errors";
    String PREF_DAY_NIGHT_MODE = "day night mode";
    String PREF_HINTS = "hints";

    SaveData(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setText(String text) {
        String placeToSave = PREF_WORDS;
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(placeToSave, text);
        prefEditor.apply();
    }

    public String getText() {
        return sharedPreferences.getString(PREF_WORDS, "error");
    }

    public void setHints(int hints) {
        String placeToSave = PREF_HINTS;
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putInt(placeToSave, hints);
        prefEditor.apply();
    }

    public int getHints() {
        return sharedPreferences.getInt(PREF_HINTS, 0);
    }


    public void setMistakes(String text) {
        String placeToSave = PREF_ERRORS;
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(placeToSave, text);
        prefEditor.apply();
    }

    public String getMistakes() {
        return sharedPreferences.getString(PREF_ERRORS, "");
    }

    public void setDayNightMode() {
        String placeToSave = PREF_DAY_NIGHT_MODE;
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putInt(placeToSave, AppCompatDelegate.getDefaultNightMode());
        prefEditor.apply();
    }

    public int getDayNightMode() {
        return sharedPreferences.getInt(PREF_DAY_NIGHT_MODE, AppCompatDelegate.getDefaultNightMode());
    }

}
