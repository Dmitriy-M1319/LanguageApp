package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.languageapp.models.TestAttempt;
import com.example.languageapp.models.TestVariant;
import com.example.languageapp.repositories.testattempt.TestAttemptDatabaseRepository;

import java.time.LocalDateTime;

public class TestResultActivity extends AppCompatActivity {

    private Button homeButton;
    private TestAttemptDatabaseRepository testRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        TestAttempt attempt;
        Bundle extras = getIntent().getExtras();
        attempt = (TestAttempt) extras.getSerializable(TestAttempt.class.getSimpleName());
        testRepository = new TestAttemptDatabaseRepository(this);

        homeButton = findViewById(R.id.home);
        TextView dateText = findViewById(R.id.test_date);
        TextView percentText = findViewById(R.id.percents);
        TextView markText = findViewById(R.id.mark);

        LocalDateTime dateTime = LocalDateTime.parse(attempt.getTestDate());
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder.append("Дата тестирования: ");
        dateBuilder.append(dateTime.toLocalDate().toString());
        dateText.setText(dateBuilder.toString());

        percentText.setText("Правильно дан ответ на " + String.valueOf(attempt.getPercentResult()) + "% вопросов" );
        if(attempt.getPercentResult() < 50) {
            markText.setText("Оценка: неудовлетворительно");
        } else if(attempt.getPercentResult() >= 50 && attempt.getPercentResult() < 70) {
            markText.setText("Оценка: удовлетворительно");
        } else if(attempt.getPercentResult() >= 70 && attempt.getPercentResult() < 90) {
            markText.setText("Оценка: хорошо");
        } else {
            markText.setText("Оценка: отлично");
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRepository.open();
                testRepository.insert(attempt);
                testRepository.close();
                goHome();
            }
        });
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}