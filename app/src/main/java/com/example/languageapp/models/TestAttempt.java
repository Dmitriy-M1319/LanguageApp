package com.example.languageapp.models;

import java.util.Date;

/**
 * Модель попытки теста на правильное определение иностранных слов и их переводов
 */
public class TestAttempt {

    /**
     * ID попытки тестирования для ее хранения в базе данных
     */
    private int id;
    /**
     * Дата прохождения теста
     */
    private Date testDate;
    /**
     * Результат попытки в процентах (от 0 до 100)
     */
    private int percentResult;
    /**
     * Вариант тестирования слов (знание иностранных или их переводов)
     */
    private TestVariant variant;

    public TestAttempt(int id, Date date, int percentResult, TestVariant variant) {
        this.id = id;
        this.testDate = date;
        this.percentResult = percentResult;
        this.variant = variant;
    }

    public int getId() {
        return id;
    }

    public Date getTestDate() {
        return testDate;
    }

    public int getPercentResult() {
        return percentResult;
    }

    public TestVariant getVariant() {
        return variant;
    }
}
