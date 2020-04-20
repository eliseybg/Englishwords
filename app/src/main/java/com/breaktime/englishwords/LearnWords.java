package com.breaktime.englishwords;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class LearnWords extends AppCompatActivity implements TextToSpeech.OnInitListener {
    String PREFS_FILE = "wordsSave";
    SaveData saveData;
    String line = "";
    ArrayList<String> engWords = new ArrayList<>();
    ArrayList<String> rusWords = new ArrayList<>();
    ArrayList<String> errors = new ArrayList<>();
    Button hintsBtn;
    Voice voice;
    int i = 0;
    int hints = 0;
    int hintPosition = 0;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_words);
        saveData = new SaveData(getSharedPreferences(PREFS_FILE, MODE_PRIVATE));
        getSupportActionBar().hide();
        getLine();
        getArrays();
        setWord();
        new HideNavigation(getWindow());
        hints = saveData.getHints();
        hintsBtn = findViewById(R.id.btn_hints);
        setHintStyle();
        textToSpeech = new TextToSpeech(this, this);

        Set<String> set = Collections.emptySet();
        voice = new Voice("en-US-language", Locale.US, Voice.QUALITY_VERY_HIGH, Voice.LATENCY_VERY_LOW, true, set);
        textToSpeech.setVoice(voice);
        textToSpeech.setLanguage(Locale.US);
    }

    public void getArrays() {
        GetWords getWords = new GetWords(line);
        engWords = getWords.getEngWords();
        rusWords = getWords.getRusWords();
    }

    public void getLine() {
        line = saveData.getText();
        TextView textView = findViewById(R.id.textViewWords);
        textView.setText(line);
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void setWordsLayout(View view) {
        hideKeyboard(this);
        Button btnWords = findViewById(R.id.btnWordsLayout);
        Button btnCheck = findViewById(R.id.btnCheckLayout);
        btnWords.setBackgroundColor(Color.parseColor("#DDC5C5C5"));
        btnCheck.setBackgroundColor(Color.parseColor("#83D3D3D3"));

        LinearLayout linearLayoutHints = findViewById(R.id.hint_buttons);
        linearLayoutHints.setVisibility(View.GONE);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.GONE);
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setVisibility(View.VISIBLE);
        TextView textViewSize = findViewById(R.id.left_size);
        textViewSize.setVisibility(View.GONE);
        TextView textViewLeft = findViewById(R.id.text_left);
        textViewLeft.setVisibility(View.GONE);
    }

    public void setCheckLayout(View view) {
        Button btnWords = findViewById(R.id.btnWordsLayout);
        Button btnCheck = findViewById(R.id.btnCheckLayout);
        btnWords.setBackgroundColor(Color.parseColor("#83D3D3D3"));
        btnCheck.setBackgroundColor(Color.parseColor("#DDC5C5C5"));

        LinearLayout linearLayoutHints = findViewById(R.id.hint_buttons);
        linearLayoutHints.setVisibility(View.VISIBLE);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.VISIBLE);
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.setVisibility(View.GONE);
        TextView textViewSize = findViewById(R.id.left_size);
        textViewSize.setVisibility(View.VISIBLE);
        TextView textViewLeft = findViewById(R.id.text_left);
        textViewLeft.setVisibility(View.VISIBLE);
    }

    public void setWord() {
        hintPosition = 0;
        i = (int) (Math.random() * (rusWords.size()));
        TextView textView = findViewById(R.id.textViewRus);
        TextView textViewSize = findViewById(R.id.left_size);
        textViewSize.setText(rusWords.size() + "");
        textView.setText(rusWords.get(i));
    }

    public void pronunciationSlow(View view) {
        textToSpeech.setSpeechRate(0.7f);
        textToSpeech.setLanguage(Locale.ENGLISH);
        textToSpeech.speak(engWords.get(i), TextToSpeech.QUEUE_FLUSH, null);
        System.out.println(textToSpeech.getVoice());
    }

    public void pronunciation(View view) {
        textToSpeech.setSpeechRate(1.2f);
        textToSpeech.setLanguage(Locale.ENGLISH);
        textToSpeech.speak(engWords.get(i), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData.setHints(hints);
    }

    public void setHint(View view) {
        if (hints > 0) {
            hints--;
            hintPosition++;
            EditText editText = findViewById(R.id.editTextEng);
            editText.setText(engWords.get(i).substring(0, hintPosition));
            editText.setSelection(editText.getText().length());
            setHintStyle();
        }
    }

    public void setHintStyle() {
        if (hints == 0) {
            hintsBtn.setBackground(getDrawable(R.drawable.hint));
        } else {
            hintsBtn.setBackground(getDrawable(R.drawable.button_hint));
            hintsBtn.setText(hints + "");
        }
    }

    public void checkWords(View view) {
        EditText editText = findViewById(R.id.editTextEng);
        String input = editText.getText().toString();
        editText.setText("");
        input = input.toLowerCase();
        if (input.equals(engWords.get(i))) {
            hints++;
            setHintStyle();
            rusWords.remove(i);
            engWords.remove(i);
            if (rusWords.size() != 0)
                setWord();
            else {
                endWords();
            }
        } else {
            errors.add(engWords.get(i) + " - " + rusWords.get(i));
            System.out.println(engWords.get(i) + " - " + rusWords.get(i));
            System.out.println(errors.get(errors.size() - 1));
            saveErrors();
            setWord();
        }
    }

    public void endWords() {
        TextView textView = findViewById(R.id.textViewRus);
        TextView textViewSize = findViewById(R.id.left_size);
        textViewSize.setText(0 + "");
        textView.setText("No more words left");
        EditText editText = findViewById(R.id.editTextEng);
//        editText.setFocusableInTouchMode(false);
//        editText.setEnabled(false);
        Button button = findViewById(R.id.btnCheck);
        button.setClickable(false);
    }

    public void saveErrors() {
        Set<String> set = new LinkedHashSet<>(errors);
        String errorsLine = "";
        ArrayList<String> errors = new ArrayList<>(set);
        int i;
        for (i = 0; i < errors.size() - 1; i++)
            errorsLine += errors.get(i) + "\n";
        errorsLine += errors.get(i);
        System.out.println(errorsLine);
        SaveData saveData = new SaveData(getSharedPreferences(PREFS_FILE, MODE_PRIVATE));
        saveData.setMistakes(errorsLine);
    }

    @Override
    public void onInit(int status) {

    }
}
