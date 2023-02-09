package com.example.languageapp.models;

import java.io.Serializable;

/**
 * Перечесление, которое определяет варианты тестирования со словами
 */
public enum TestVariant implements Serializable {
    TEST_FOREIGN_WORDS,
    TEST_TRANSLATED_WORD;

    public static TestVariant getVariantByNumber(int n) {
        switch (n) {
            case 0:
                return TEST_FOREIGN_WORDS;
            case 1:
                return TEST_TRANSLATED_WORD;
            default:
                return null;
        }
    }

    public int toInt() {
       if (this == TEST_FOREIGN_WORDS)
           return 0;
       else
           return 1;
    }
}
