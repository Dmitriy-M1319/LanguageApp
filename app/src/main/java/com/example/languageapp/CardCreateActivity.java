package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.languageapp.models.Card;
import com.example.languageapp.repositories.card.CardDatabaseRepository;

public class CardCreateActivity extends AppCompatActivity {

    private EditText foreignWord;
    private EditText translatedWord;
    private CardDatabaseRepository cardRepository;
    private long cardId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_create);
        foreignWord = findViewById(R.id.foreignWord);
        translatedWord = findViewById(R.id.translatedWord);
        cardRepository = new CardDatabaseRepository(this);
    }

    public void onCreateCardClick(View view) {
        String foreign = foreignWord.getText().toString();
        String translated = translatedWord.getText().toString();
        if(foreign.equals("") || translated.equals(""))
            return;

        Card card = new Card(cardId, foreign, translated);
        cardRepository.open();
        cardRepository.insert(card);
        cardRepository.close();
        goHome();
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}