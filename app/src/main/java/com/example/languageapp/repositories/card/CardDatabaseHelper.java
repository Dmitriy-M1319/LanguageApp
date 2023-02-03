package com.example.languageapp.repositories.card;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CardDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "language_app";
    private static final int SCHEMA = 1;
    public static final String TABLE = "cards";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOREIGN_WORD = "foreign_word";
    public static final String COLUMN_TRANSLATED_WORD = "translated_word";

    public CardDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOREIGN_WORD + " TEXT," + COLUMN_TRANSLATED_WORD + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }
}
