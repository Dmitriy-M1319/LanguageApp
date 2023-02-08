package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.languageapp.CardUpdateActivity;
import com.example.languageapp.R;
import com.example.languageapp.TestProcessActivity;
import com.example.languageapp.models.TestVariant;

public class TestVariantActivity extends AppCompatActivity {

    private TestVariant variant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_variant);

        RadioGroup group = findViewById(R.id.radios);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               switch (i) {
                   case R.id.foreign:
                       variant = TestVariant.TEST_FOREIGN_WORDS;
                       break;
                   case R.id.translated:
                       variant = TestVariant.TEST_TRANSLATED_WORD;
                       break;
               }
            }
        });

        Button testStartBtn = findViewById(R.id.test_start_button);
        testStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestProcessActivity.class);
                intent.putExtra("variant", variant.toInt());
                startActivity(intent);
            }
        });
    }
}