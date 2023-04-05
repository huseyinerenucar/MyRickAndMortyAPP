package com.example.rickandmorty.util;

public class StaticFunctions {
    public static String textFixer(String textInput){
        String[] words = textInput.trim().split("\\s+");

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                result.append(firstLetter).append(restOfWord).append(" ");
            }
        }
        return result.toString().trim();
    }
}
