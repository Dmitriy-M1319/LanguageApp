package com.example.languageapp.repositories.testattempt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestAttemptDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "language_app";
    private static final int SCHEMA = 1;
    public static final String TABLE = "test_attempts";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEST_DATE = "test_date";
    public static final String COLUMN_PERCENT_RESULT = "percent_result";
    public static final String COLUMN_VARIANT = "test_variant";

    public TestAttemptDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEST_DATE + " TEXT,"
        + COLUMN_PERCENT_RESULT + " INTEGER," + COLUMN_VARIANT + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }
}
