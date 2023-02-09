package com.example.languageapp.repositories.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CommonDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "language_app.db";
    private static final int SCHEMA = 1;


    public CommonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
        getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.w(CommonDatabaseHelper.class.getSimpleName(), "called onCreate for SCHEMA " + String.valueOf(SCHEMA));
        sqLiteDatabase.execSQL(DbCards.getSql());
        sqLiteDatabase.execSQL(DbTestCards.getSql());
        sqLiteDatabase.execSQL(DbTestAttempt.getSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(CommonDatabaseHelper.class.getSimpleName(), "called onUpgrade for SCHEMA " + String.valueOf(SCHEMA));
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbCards.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbTestCards.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DbTestAttempt.TABLE);
        onCreate(sqLiteDatabase);
    }
}
