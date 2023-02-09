package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.languageapp.adapters.TestCardAdapter;
import com.example.languageapp.models.TestAttempt;
import com.example.languageapp.models.TestCard;
import com.example.languageapp.repositories.testcard.TestCardDatabaseRepository;

import java.util.List;

public class TestCardShowActivity extends AppCompatActivity {

    private TextView header;
    private ListView cardList;
    private TestCardDatabaseRepository testCardRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_card_show);

        Bundle extras = getIntent().getExtras();
        TestAttempt attempt = (TestAttempt) extras.getSerializable("attempt");
        if(attempt == null) {
            Log.w(TestCardShowActivity.class.getSimpleName(), "Extras: null");
        }
        header = findViewById(R.id.header);
        header.setText("Вопросы из попытки № " + attempt.getId());
        testCardRepository = new TestCardDatabaseRepository(this);
        testCardRepository.open();
        List<TestCard> testCards = testCardRepository.getTestCardsByTestAttempt(attempt);
        testCardRepository.close();

        cardList = findViewById(R.id.cardList);
        TestCardAdapter cardAdapter = new TestCardAdapter(this, R.layout.test_card_item, testCards);
        cardList.setAdapter(cardAdapter);
    }
}