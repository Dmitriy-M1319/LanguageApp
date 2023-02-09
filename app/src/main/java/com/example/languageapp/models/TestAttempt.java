package com.example.languageapp.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Модель попытки теста на правильное определение иностранных слов и их переводов
 */
public class TestAttempt implements Serializable {

    /**
     * ID попытки тестирования для ее хранения в базе данных
     */
    private int id;
    /**
     * Дата прохождения теста (ДД-ММ-ГГГГ:ЧЧ-ММ)
     */
    private String testDate;
    /**
     * Результат попытки в процентах (от 0 до 100)
     */
    private int percentResult;
    /**
     * Вариант тестирования слов (знание иностранных или их переводов)
     */
    private TestVariant variant;

    public TestAttempt(int id, String date, int percentResult, TestVariant variant) {
        this.id = id;
        this.testDate = date;
        this.percentResult = percentResult;
        this.variant = variant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestDate() {
        return testDate;
    }
    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public int getPercentResult() {
        return percentResult;
    }

    public void setPercentResult(int percentResult) {
        this.percentResult = percentResult;
    }

    public TestVariant getVariant() {
        return variant;
    }

    public void setVariant(TestVariant variant) {
        this.variant = variant;
    }
}
