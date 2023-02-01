package com.example.languageapp.models;

import androidx.annotation.NonNull;

/**
 * Модель карточки, которая содержит пары "слово" - "перевод"
 */
public class Card {
    /**
     * ID карточки для ее хранения в базе данных
     */
    private long id;
    /**
     * Слово из иностранного языка
     */
    private String foreignWord;
    /**
     * Слово, которое является переводом иностранного слова на родной язык
     */
    private String translatedWord;

    public Card(long id, String foreignWord, String translatedWord) {
        this.foreignWord = foreignWord;
        this.id = id;
        this.translatedWord = translatedWord;
    }

    public long getId() {
        return id;
    }

    public String getForeignWord() {
        return foreignWord;
    }

    public void setForeignWord(String foreignWord) {
        this.foreignWord = foreignWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    @NonNull
    @Override
    public String toString() {
        return foreignWord + " -> " + translatedWord;
    }
}
