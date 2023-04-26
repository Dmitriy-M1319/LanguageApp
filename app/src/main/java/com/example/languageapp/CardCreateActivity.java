package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.languageapp.api.APIClient;
import com.example.languageapp.api.TranslationService;
import com.example.languageapp.api.models.TranslationRequest;
import com.example.languageapp.api.models.TranslationResponse;
import com.example.languageapp.models.Card;
import com.example.languageapp.repositories.card.CardDatabaseRepository;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CardCreateActivity extends AppCompatActivity {

    private EditText foreignWord;
    private EditText translatedWord;
    private CardDatabaseRepository cardRepository;
    private Spinner foreingSpinner;
    private Spinner localSpinner;
    private String[] languages;
    private String foreignLang;
    private String translatedLang;
    private EditText searchWord;
    private long cardId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_create);
        foreignWord = findViewById(R.id.foreignWord);
        translatedWord = findViewById(R.id.translatedWord);
        cardRepository = new CardDatabaseRepository(this);
        searchWord = findViewById(R.id.netForeigndWord);
        foreingSpinner = findViewById(R.id.foreignSpinner);
        localSpinner = findViewById(R.id.localSpinner);
        languages = getResources().getStringArray(R.array.languages_short);
        AdapterView.OnItemSelectedListener foreignListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                foreignLang = languages[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };
        AdapterView.OnItemSelectedListener translatedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                translatedLang = languages[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };

        foreingSpinner.setOnItemSelectedListener(foreignListener);
        localSpinner.setOnItemSelectedListener(translatedListener);
    }

    public void onCreateCardClick(View view) {
        String foreign = foreignWord.getText().toString().trim();
        String translated = translatedWord.getText().toString().trim();
        if(foreign.equals("") || translated.equals(""))
            return;

        Card card = new Card(cardId, foreign, translated);
        cardRepository.open();
        cardRepository.insert(card);
        cardRepository.close();
        goHome();
    }



    public void onFindWordClick(View view) {
        TranslationRequest request = new TranslationRequest(foreignLang,
                translatedLang, searchWord.getText().toString().trim());
        Retrofit retrofit = APIClient.getClient();
        TranslationService service = retrofit.create(TranslationService.class);
        Call<TranslationResponse> response = service.translate(request);
        response.enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
               TranslationResponse translate = response.body();
                Snackbar.make(view, "Перевод: " + translate.getWord(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                Snackbar.make(view, "Не удалось выполнить перевод", Snackbar.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}