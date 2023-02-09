package com.example.languageapp.repositories.testattempt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.languageapp.models.TestAttempt;
import com.example.languageapp.models.TestVariant;
import com.example.languageapp.repositories.helper.CommonDatabaseHelper;
import com.example.languageapp.repositories.helper.DbTestAttempt;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для операций с попытками тестирования в базе данных
 */
public class TestAttemptDatabaseRepository {
    private CommonDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public TestAttemptDatabaseRepository(Context context) {
        dbHelper = new CommonDatabaseHelper(context.getApplicationContext());
    }

    /**
     * Открыть соединение для использования базы данных
     * @return Экземпляр репозитория TestAttemptDatabaseRepository
     */
    public TestAttemptDatabaseRepository open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Закрыть соединение с базой данных
     */
    public void close() {
        dbHelper.close();
    }


    private Cursor getEntries() {
        String[] columns = new String[] {DbTestAttempt.COLUMN_ID,
                DbTestAttempt.COLUMN_TEST_DATE,
                DbTestAttempt.COLUMN_PERCENT_RESULT,
                DbTestAttempt.COLUMN_VARIANT};
        return database.query(DbTestAttempt.TABLE, columns, null, null, null, null, null);
    }

    /**
     * Получить все попытки тестирования в виде списка
     * @return Список экземпляров TestAttempt
     */
    public List<TestAttempt> getAllAttempts() {
        List<TestAttempt> attempts = new ArrayList<>();
        Cursor cursor = getEntries();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbTestAttempt.COLUMN_ID));
            int percent = cursor.getInt(cursor.getColumnIndex(DbTestAttempt.COLUMN_PERCENT_RESULT));
            String date = cursor.getString(cursor.getColumnIndex(DbTestAttempt.COLUMN_TEST_DATE));
            TestVariant variant = TestVariant.getVariantByNumber(cursor.getInt(cursor.getColumnIndex(DbTestAttempt.COLUMN_VARIANT)));
            attempts.add(new TestAttempt(id, date, percent, variant));
        }
        cursor.close();
        return attempts;
    }

    /**
     * Получить попытку по ее идентификатору ID
     * @param id Идентификатор ID попытки
     * @return Экземпляр класса TestAttempt
     */
    public TestAttempt getAttemptById(int id) {
        TestAttempt testAttempt = null;
        String query = "SELECT * FROM " + DbTestAttempt.TABLE + " WHERE " + DbTestAttempt.COLUMN_ID + "=?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()) {
            int percent = cursor.getInt(cursor.getColumnIndex(DbTestAttempt.COLUMN_PERCENT_RESULT));
            String date = cursor.getString(cursor.getColumnIndex(DbTestAttempt.COLUMN_TEST_DATE));
            TestVariant variant = TestVariant.getVariantByNumber(cursor.getInt(cursor.getColumnIndex(DbTestAttempt.COLUMN_VARIANT)));
            testAttempt = new TestAttempt(id, date, percent, variant);
        }
        cursor.close();
        return testAttempt;
    }

    /**
     * Добавить новую попытку тестирования в базу данных
     * @param attempt Новая попытка тестирования
     * @return Идентификатор ID добавленной в базу попытки
     */
    public long insert(TestAttempt attempt) {
       if(attempt == null)
           return -1;

       ContentValues values = new ContentValues();
       values.put(DbTestAttempt.COLUMN_TEST_DATE, attempt.getTestDate());
       values.put(DbTestAttempt.COLUMN_PERCENT_RESULT, attempt.getPercentResult());
       values.put(DbTestAttempt.COLUMN_VARIANT, attempt.getVariant().toInt());
       return database.insert(DbTestAttempt.TABLE, null, values);
    }

    // Обновление прошедших попыткок не требуется

    /**
     * Удалить существующую запись попытки тестирования из базы данных
     * @param attempt Удаляемая попытка тестирования
     * @return Идентификатор ID удаленной записи
     */
    public long delete(TestAttempt attempt) {
        if(attempt == null)
            return -1;

        String whereCondition = DbTestAttempt.TABLE + "=;";
        String[] whereArgs = new String[]{String.valueOf(attempt.getId())};
        return database.delete(DbTestAttempt.TABLE, whereCondition, whereArgs);
    }

}
