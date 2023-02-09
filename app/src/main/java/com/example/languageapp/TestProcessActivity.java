package com.example.languageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.languageapp.models.Card;
import com.example.languageapp.models.TestAttempt;
import com.example.languageapp.models.TestCard;
import com.example.languageapp.models.TestVariant;
import com.example.languageapp.repositories.card.CardDatabaseRepository;
import com.example.languageapp.repositories.testattempt.TestAttemptDatabaseRepository;
import com.example.languageapp.repositories.testcard.TestCardDatabaseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestProcessActivity extends AppCompatActivity {

    private TestAttempt newAttempt;
    private List<TestCard> attemptCards;
    private TestCard currentTestCard;
    private List<Card> cardsForTesting;
    private Card currentCard;
    private TextView question;
    private TextView askField;
    private Button nextButton;
    private EditText answerField;
    private int questionNumber = 1;
    private TestCardDatabaseRepository testCardRepository;
    private TestAttemptDatabaseRepository testRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_process);

        TestVariant variant;
        Bundle extras = getIntent().getExtras();
        variant = TestVariant.getVariantByNumber(extras.getInt("variant"));
        testCardRepository = new TestCardDatabaseRepository(this);
        testRepository = new TestAttemptDatabaseRepository(this);

        newAttempt = new TestAttempt(0, LocalDateTime.now().toString(), 0, variant);
        attemptCards = new ArrayList<>();
        loadCards();

        question = findViewById(R.id.question_n);
        askField = findViewById(R.id.ask);
        answerField = findViewById(R.id.answer);
        nextButton = findViewById(R.id.next_btn);

        createNewTestPage();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkResult();
                switch (cardsForTesting.size()) {
                    case 1:
                        nextButton.setText("Завершить");
                        break;
                    case 0:
                        closeTestAttempt();
                        return;
                }
                createNewTestPage();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    protected void createNewTestPage() {
        currentCard = cardsForTesting.get((int)(Math.random() * cardsForTesting.size()));
        question.setText("Вопрос № " + String.valueOf(questionNumber));
        answerField.setText("");
        String testedWord = newAttempt.getVariant() == TestVariant.TEST_FOREIGN_WORDS ? currentCard.getForeignWord() : currentCard.getTranslatedWord();
        currentTestCard = new TestCard(0, testedWord, "", false, newAttempt );
        askField.setText("Перевод слова " + testedWord + "?");
    }

    protected void checkResult() {
        currentTestCard.setUserAnswer(answerField.getText().toString().trim());
        String trueResult = newAttempt.getVariant() == TestVariant.TEST_FOREIGN_WORDS ? currentCard.getTranslatedWord() : currentCard.getForeignWord();
        currentTestCard.setResult(currentTestCard.getUserAnswer().equals(trueResult));
        attemptCards.add(currentTestCard);
        cardsForTesting.remove(currentCard);
        questionNumber++;
    }

    protected void closeTestAttempt() {
        int totalCount = attemptCards.size();
        int goodResultsCount = 0;
        for (TestCard testCard: attemptCards) {
            if(testCard.isResult()) {
                goodResultsCount++;
            }
        }
        double percents = (double)goodResultsCount / totalCount * 100;
        newAttempt.setPercentResult((int)percents);

        testRepository.open();
        long i = testRepository.insert(newAttempt);
        testRepository.close();

        testCardRepository.open();
        for (TestCard testCard: attemptCards) {
            testCard.getTestAttempt().setId((int)i);
            testCardRepository.insert(testCard);
        }
        testCardRepository.close();

        Intent intent = new Intent(getApplicationContext(), TestResultActivity.class);
        intent.putExtra(TestAttempt.class.getSimpleName(), newAttempt);
        startActivity(intent);
    }

    protected void loadCards() {
        CardDatabaseRepository cardRepository = new CardDatabaseRepository(this);
        cardRepository.open();
        cardsForTesting = cardRepository.getAllCards();
        cardRepository.close();
    }
}