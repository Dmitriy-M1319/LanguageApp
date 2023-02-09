package com.example.languageapp.repositories.card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.languageapp.models.Card;
import com.example.languageapp.repositories.helper.CommonDatabaseHelper;
import com.example.languageapp.repositories.helper.DbCards;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для операций с карточками в базе данных
 */
public class CardDatabaseRepository {
    private CommonDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public CardDatabaseRepository(Context context) {
        dbHelper = new CommonDatabaseHelper(context.getApplicationContext());
    }

    /**
     * Открыть соединение для использования базы данных
     * @return Экземпляр репозитория CardDatabaseRepository
     */
    public CardDatabaseRepository open() {
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
        String[] columns = new String[] {DbCards.COLUMN_ID,
                DbCards.COLUMN_FOREIGN_WORD,
                DbCards.COLUMN_TRANSLATED_WORD};
        return database.query(DbCards.TABLE, columns, null, null, null, null, null);
    }

    /**
     * Получить список всех карточек в приложении
     * @return Список экземпляров класса Card
     */
    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        Cursor cursor = getEntries();
        while (cursor.moveToNext()) {
           int id = cursor.getInt(cursor.getColumnIndex(DbCards.COLUMN_ID));
           String foreign = cursor.getString(cursor.getColumnIndex(DbCards.COLUMN_FOREIGN_WORD));
           String translated = cursor.getString(cursor.getColumnIndex(DbCards.COLUMN_TRANSLATED_WORD));
           cards.add(new Card(id, foreign, translated));
        }
        cursor.close();
        return cards;
    }

    /**
     * Получить запись карточки по ее ID
     * @param id Идентификатор карточки в базе данных
     * @return Экземпляр класса Card
     */
    public Card getCardById(long id) {
        Card card = null;
        String query = "SELECT * FROM " + DbCards.TABLE + " WHERE " + DbCards.COLUMN_ID + "=?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()) {
            String foreign = cursor.getString(cursor.getColumnIndex(DbCards.COLUMN_FOREIGN_WORD));
            String translated = cursor.getString(cursor.getColumnIndex(DbCards.COLUMN_TRANSLATED_WORD));
            card = new Card(id, foreign, translated);
        }
        cursor.close();
        return card;
    }

    /**
     * Добавить карточку в базу данных
     * @param card Экземпляр класса Card
     * @return Идентификатор ID новосозданной карточки
     */
    public long insert(Card card) {
        if(card == null)
            return -1;
        ContentValues cv = new ContentValues();
        cv.put(DbCards.COLUMN_FOREIGN_WORD, card.getForeignWord());
        cv.put(DbCards.COLUMN_TRANSLATED_WORD, card.getTranslatedWord());
        return database.insert(DbCards.TABLE, null, cv);
    }

    /**
     * Обновить имеющуюся в базе данных карточку
     * @param card Экземпляр класса Card - обновленная программно карточка
     * @return Идентификатор ID обновленной карточки
     */
    public long update(Card card) {
        if(card == null)
            return -1;
        String whereCondidion = DbCards.COLUMN_ID + "=" + card.getId();
        ContentValues cv = new ContentValues();
        cv.put(DbCards.COLUMN_FOREIGN_WORD, card.getForeignWord());
        cv.put(DbCards.COLUMN_TRANSLATED_WORD, card.getTranslatedWord());
        return database.update(DbCards.TABLE, cv, whereCondidion, null);
    }

    /**
     * Удалить карточку из базы данных
     * @param card
     * @return
     */
    public long delete(Card card) {
        if(card == null)
            return -1;
        String whereCondidion = DbCards.COLUMN_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(card.getId())};
        return database.delete(DbCards.TABLE, whereCondidion, whereArgs);
    }
}
