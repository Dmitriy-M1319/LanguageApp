package com.example.languageapp.api.models;

public class TranslationRequest {
    private String from;
    private String to;
    private String word;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public TranslationRequest(String from, String to, String word) {
        this.from = from;
        this.to = to;
        this.word = word;
    }
}
