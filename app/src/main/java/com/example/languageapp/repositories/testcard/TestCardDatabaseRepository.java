package com.example.languageapp.repositories.testcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.languageapp.models.TestAttempt;
import com.example.languageapp.models.TestCard;
import com.example.languageapp.repositories.card.CardDatabaseRepository;
import com.example.languageapp.repositories.helper.CommonDatabaseHelper;
import com.example.languageapp.repositories.helper.DbTestCards;
import com.example.languageapp.repositories.testattempt.TestAttemptDatabaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Репозиторий для операции с особыми карточками в базе данных
 */
public class TestCardDatabaseRepository {
    private CommonDatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private TestAttemptDatabaseRepository testRepository;

    public TestCardDatabaseRepository(Context context) {
        dbHelper = new CommonDatabaseHelper(context.getApplicationContext());
        testRepository = new TestAttemptDatabaseRepository(context.getApplicationContext());
    }

    public TestCardDatabaseRepository open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getAllEntries() {
        String[] columns = new String[]{DbTestCards.COLUMN_ID,
                DbTestCards.COLUMN_CHECKED_WORD,
        DbTestCards.COLUMN_USER_ANSWER,
        DbTestCards.COLUMN_RESULT,
        DbTestCards.COLUMN_TEST_ATTEMPT_ID};
        return database.query(DbTestCards.TABLE, columns, null, null, null, null, null);
    }

    /**
     * Получить все особые карточки
     * @return Список List с экземплярами TestCard
     */
    public List<TestCard> getAllTestCards() {
        List<TestCard> cards = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbTestCards.COLUMN_ID));
            String word = cursor.getString(cursor.getColumnIndex(DbTestCards.COLUMN_CHECKED_WORD));
            String answer = cursor.getString(cursor.getColumnIndex(DbTestCards.COLUMN_USER_ANSWER));
            boolean result = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(DbTestCards.COLUMN_RESULT)));
            int testId = cursor.getInt(cursor.getColumnIndex(DbTestCards.COLUMN_TEST_ATTEMPT_ID));
            testRepository.open();
            TestAttempt attempt = testRepository.getAttemptById(testId);
            testRepository.close();
            cards.add(new TestCard(id, word, answer, result, attempt));
        }
        cursor.close();
        return cards;
    }

    /**
     * Получить все карточки, которые соответствуют определенной попытке тестирования
     * @param attempt Попытка тестирования
     * @return Список List с экземплярами TestCard
     */
    public List<TestCard> getTestCardsByTestAttempt(TestAttempt attempt) {
        List<TestCard> cards = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + DbTestCards.TABLE + " where "
                + DbTestCards.COLUMN_TEST_ATTEMPT_ID + "=?", new String[]{String.valueOf(attempt.getId())});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbTestCards.COLUMN_ID));
            String word = cursor.getString(cursor.getColumnIndex(DbTestCards.COLUMN_CHECKED_WORD));
            String answer = cursor.getString(cursor.getColumnIndex(DbTestCards.COLUMN_USER_ANSWER));
            boolean result = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(DbTestCards.COLUMN_RESULT)));
            int testId = cursor.getInt(cursor.getColumnIndex(DbTestCards.COLUMN_TEST_ATTEMPT_ID));
            cards.add(new TestCard(id, word, answer, result, attempt));
        }
        cursor.close();
        return cards;
    }

    /**
     * Получить все карточки, которые соответствуют определенной попытке тестирования и результату
     * @param attempt Попытка тестирования
     * @param result Результат ответа
     * @return Список List с экземплярами TestCard
     */
    public List<TestCard> getTestCardsByAttemptAndResult(TestAttempt attempt, boolean result) {
        if (attempt == null) {
            return null;
        }
        List<TestCard> cards = getTestCardsByTestAttempt(attempt);
        return cards.stream().filter(c -> c.isResult() == result).collect(Collectors.toList());
    }

    /**
     * Добавить новую особую карточку в базу данных
     * @param testCard Добавляемая особая карточка
     * @return Индекс добавленной карточки
     */
    public long insert(TestCard testCard) {
        if(testCard == null) {
            return -1;
        }
        ContentValues cv = new ContentValues();
        cv.put(DbTestCards.COLUMN_CHECKED_WORD, testCard.getCheckedWord());
        cv.put(DbTestCards.COLUMN_RESULT, String.valueOf(testCard.isResult()));
        cv.put(DbTestCards.COLUMN_USER_ANSWER, testCard.getUserAnswer());
        cv.put(DbTestCards.COLUMN_TEST_ATTEMPT_ID, testCard.getTestAttempt().getId());
        return database.insert(DbTestCards.TABLE, null, cv);
    }
}