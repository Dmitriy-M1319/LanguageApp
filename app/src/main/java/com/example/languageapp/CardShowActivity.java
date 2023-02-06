package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.languageapp.models.Card;
import com.example.languageapp.repositories.card.CardDatabaseRepository;

import java.util.List;

public class CardShowActivity extends AppCompatActivity {

    ListView cardList;
    ArrayAdapter<Card> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_show);

        cardList = findViewById(R.id.cardList);
        cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Card card = arrayAdapter.getItem(i);
                if(card != null) {
                    Intent intent = new Intent(getApplicationContext(), CardUpdateActivity.class);
                    intent.putExtra("id", card.getId());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CardDatabaseRepository cardDatabaseRepository = new CardDatabaseRepository(this);
        cardDatabaseRepository.open();
        List<Card> cards = cardDatabaseRepository.getAllCards();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards);
        cardList.setAdapter(arrayAdapter);
        cardDatabaseRepository.close();
    }
}