package com.example.languageapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.languageapp.R;
import com.example.languageapp.models.TestAttempt;
import com.example.languageapp.models.TestCard;

import java.time.LocalDateTime;
import java.util.List;

public class TestCardAdapter extends ArrayAdapter {
    private List<TestCard> cards;
    private int resource;
    private LayoutInflater inflater;

    public TestCardAdapter(Context context, int resource, List<TestCard> cards) {
        super(context, resource, cards);
        this.resource = resource;
        this.cards = cards;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = inflater.inflate(resource, viewGroup, false);
        TextView testCardNumber = view.findViewById(R.id.test_card_number);
        TextView testCardChecked = view.findViewById(R.id.test_card_checked);
        TextView testCardAnswer = view.findViewById(R.id.test_card_answer);
        TextView testCardResult = view.findViewById(R.id.test_card_result);

        TestCard card = cards.get(position);
        testCardNumber.setText("Вопрос № " + String.valueOf(position + 1));
        testCardChecked.setText("Проверяемое слово: " + card.getCheckedWord());
        testCardAnswer.setText("Ответ : " + card.getUserAnswer());
        String textResult = card.isResult() ? "правильно" : "неправильно";
        testCardResult.setText("Результат:" + textResult);

        return view;
    }
}
