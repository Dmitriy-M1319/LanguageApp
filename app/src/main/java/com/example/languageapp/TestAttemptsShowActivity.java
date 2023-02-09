package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.languageapp.adapters.TestAttemptAdapter;
import com.example.languageapp.models.TestAttempt;
import com.example.languageapp.models.TestCard;
import com.example.languageapp.repositories.testattempt.TestAttemptDatabaseRepository;

import java.util.List;

public class TestAttemptsShowActivity extends AppCompatActivity {

    private ListView testsList;
    private List<TestAttempt> allAttempts;
    private TestAttemptDatabaseRepository testRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_attempts_show);

        testRepository = new TestAttemptDatabaseRepository(this);
        testRepository.open();
        allAttempts = testRepository.getAllAttempts();
        Log.d("test attempt", String.valueOf(allAttempts.size()));
        testRepository.close();

        testsList = findViewById(R.id.attemptsList);
        TestAttemptAdapter attemptAdapter = new TestAttemptAdapter(this, R.layout.test_attempt_item, allAttempts);
        testsList.setAdapter(attemptAdapter);
        testsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TestAttempt attempt = allAttempts.get(i);
                if(attempt != null) {
                    goTestCards(attempt);
                }
            }
        });
    }

    public void goTestCards(TestAttempt attempt) {
        Intent intent = new Intent(this, TestCardShowActivity.class);
        intent.putExtra("attempt", attempt);
        startActivity(intent);
    }
}