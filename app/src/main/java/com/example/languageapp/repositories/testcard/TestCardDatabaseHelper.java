package com.example.languageapp.repositories.testcard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestCardDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "language_app";
    private static final int SCHEMA = 1;
    public static final String TABLE = "test_cards";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PARENT_CARD_ID = "parent_card_id";
    public static final String COLUMN_USER_ANSWER = "user_answer";
    public static final String COLUMN_RESULT = "result";
    public static final String COLUMN_TEST_ATTEMPT_ID = "test_attempt_id" ;

    public TestCardDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PARENT_CARD_ID + " INTEGER,"
                + "FOREIGN KEY (" + COLUMN_PARENT_CARD_ID  + ") REFERENCES cards (id),"
                + COLUMN_USER_ANSWER + " TEXT," + COLUMN_RESULT + " TEXT,"
                + COLUMN_TEST_ATTEMPT_ID + " INTEGER," + "FOREIGN KEY (" + COLUMN_TEST_ATTEMPT_ID + ") REFERENCES test_attempts (id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }
}
