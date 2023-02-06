package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.languageapp.models.Card;
import com.example.languageapp.repositories.card.CardDatabaseRepository;

public class CardUpdateActivity extends AppCompatActivity {
    private EditText foreignWord;
    private EditText translatedWord;
    private CardDatabaseRepository cardRepository;
    private long cardId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_update);
        foreignWord = findViewById(R.id.foreignWord);
        translatedWord = findViewById(R.id.translatedWord);
        cardRepository = new CardDatabaseRepository(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cardId = extras.getLong("id");
        }

        cardRepository.open();
        Card card = cardRepository.getCardById(cardId);
        foreignWord.setText(card.getForeignWord());
        translatedWord.setText(card.getTranslatedWord());
        cardRepository.close();
    }

    public void onUpdateCardClick(View view){
        String newForeignWord = foreignWord.getText().toString();
        String newTranslatedWord = translatedWord.getText().toString();

        Card card = new Card(cardId, newForeignWord, newTranslatedWord);
        cardRepository.open();
        cardRepository.update(card);
        cardRepository.close();
        goHome();
    }

    public void onDeleteCardClick(View view){
        cardRepository.open();
        Card card = cardRepository.getCardById(cardId);
        cardRepository.delete(card);
        cardRepository.close();
        goHome();
    }
    private void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}