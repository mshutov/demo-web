package com.example.demo;

/**
 * @author <a href="mailto:mshutov@wiley.com">Mikhail Shutov</a>
 */
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
