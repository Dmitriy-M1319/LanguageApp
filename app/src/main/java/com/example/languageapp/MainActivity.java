package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button showCardsButton;
    private Button addCardButton;
    private Button startTestButton;
    private Button showAttemptsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showCardsButton = findViewById(R.id.showCardsButton);
        addCardButton = findViewById(R.id.addCardButton);
        startTestButton = findViewById(R.id.startTestButton);
        showAttemptsButton = findViewById(R.id.showAttemptsButton);
    }


    public void onShowCardsButtonClick(View view) {
        Intent intent = new Intent(this, CardShowActivity.class);
        startActivity(intent);
    }

    public void onAddCardButtonClick(View view) {
        Intent intent = new Intent(this, CardCreateActivity.class);
        startActivity(intent);
    }

    public void onStartTestButtonClick(View view) {
        Intent intent = new Intent(this, TestVariantActivity.class);
        startActivity(intent);
    }

    public void onShowAttemptsButtonClick(View view) {

    }
}