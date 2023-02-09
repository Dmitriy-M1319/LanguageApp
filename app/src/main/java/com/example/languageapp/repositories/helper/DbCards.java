package com.example.languageapp.repositories.helper;

public class DbCards {
    public static final String TABLE = "cards";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOREIGN_WORD = "foreign_word";
    public static final String COLUMN_TRANSLATED_WORD = "translated_word";

    public static String getSql() {
        return "CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOREIGN_WORD + " TEXT," + COLUMN_TRANSLATED_WORD + " TEXT);";
    }
}
