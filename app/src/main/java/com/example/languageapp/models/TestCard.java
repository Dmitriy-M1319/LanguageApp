package com.example.languageapp.models;

/**
 * Модель особой карточки, которая будет использоваться в тестировании
 */
public class TestCard {
    /**
     * ID особой карточки для хранения ее в базе данных
     */
    private int id;
    /**
     * Cлово из карточки, знания по которой проверяются в данный момент
     */
    private String checkedWord;
    /**
     * Ответ пользователя
     */
    private String userAnswer;
    /**
     * Результат (правильный ответ или неверный)
     */
    private boolean result;
    /**
     * Ссылка на попытку тестирования, которому принадлежит данная особая карточка
     */
    private TestAttempt testAttempt;

    public TestCard(int id, String checkedWord, String userAnswer, boolean result, TestAttempt testAttempt) {
       this.id = id;
       this.checkedWord = checkedWord;
       this.userAnswer = userAnswer;
       this.result = result;
       this.testAttempt = testAttempt;
    }

    public int getId() {
        return id;
    }

    public String getCheckedWord() {
        return checkedWord;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public TestAttempt getTestAttempt() {
        return testAttempt;
    }
}
