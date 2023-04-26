package com.example.languageapp.api.models;

public class TranslationResponse {
    private String word;

    public TranslationResponse(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
