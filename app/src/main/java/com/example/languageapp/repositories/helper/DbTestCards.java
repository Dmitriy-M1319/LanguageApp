package com.example.languageapp.repositories.helper;

public class DbTestCards {
    public static final String TABLE = "testCards";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CHECKED_WORD = "checked_word";
    public static final String COLUMN_USER_ANSWER = "user_answer";
    public static final String COLUMN_RESULT = "result";
    public static final String COLUMN_TEST_ATTEMPT_ID = "test_attempt_id" ;

    public static String getSql() {
        return "CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CHECKED_WORD + " TEXT,"
                + COLUMN_USER_ANSWER + " TEXT," + COLUMN_RESULT + " TEXT,"
                + COLUMN_TEST_ATTEMPT_ID + " INTEGER," + "FOREIGN KEY (" + COLUMN_TEST_ATTEMPT_ID + ") REFERENCES test_attempts (id));" ;
    }

}
