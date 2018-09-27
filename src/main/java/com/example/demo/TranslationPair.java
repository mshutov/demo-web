package com.example.demo;

public class TranslationPair {
    private final String word;
    private final String meaning;

    public TranslationPair(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }
}
