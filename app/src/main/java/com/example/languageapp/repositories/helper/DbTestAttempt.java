package com.example.languageapp.repositories.helper;

public class DbTestAttempt {
    public static final String TABLE = "testAttempts";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEST_DATE = "test_date";
    public static final String COLUMN_PERCENT_RESULT = "percent_result";
    public static final String COLUMN_VARIANT = "test_variant";
    public static String getSql() {
        return "CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TEST_DATE + " TEXT,"
                + COLUMN_PERCENT_RESULT + " INTEGER," + COLUMN_VARIANT + " INTEGER);";
    }

}
