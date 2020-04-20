package com.breaktime.englishwords;

import java.util.ArrayList;
import java.util.Scanner;

public class GetWords {

    ArrayList<String> engWords = new ArrayList<>();
    ArrayList<String> rusWords = new ArrayList<>();

    GetWords(String text){
        Scanner scan = new Scanner(text);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            line = line.toLowerCase();
            engWords.add(line.substring(0, line.indexOf("- ")).trim());
            int position = line.indexOf("- ") + 1;
            do {
                position = line.indexOf("- ", position + 1) + 1;
            }while(line.indexOf("- ", position + 1) != - 1);
            rusWords.add(line.substring(position, line.length()).trim());
        }
        scan.close();
    }

    public ArrayList<String> getEngWords() {
        return engWords;
    }

    public ArrayList<String> getRusWords() {
        return rusWords;
    }
}
