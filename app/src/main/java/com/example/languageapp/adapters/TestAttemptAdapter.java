package com.example.languageapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.languageapp.R;
import com.example.languageapp.models.TestAttempt;

import java.time.LocalDateTime;
import java.util.List;

public class TestAttemptAdapter extends ArrayAdapter<TestAttempt> {

    private LayoutInflater inflater;
    private int resource;
    private List<TestAttempt> attempts;

    public TestAttemptAdapter(Context context, int resource, List<TestAttempt> attempts) {
        super(context, resource, attempts);
        this.resource = resource;
        this.attempts = attempts;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = inflater.inflate(resource, viewGroup, false);
        TextView attemptNumber = view.findViewById(R.id.attempt_number);
        TextView attemptDate = view.findViewById(R.id.attempt_date);
        TextView attemptTime = view.findViewById(R.id.attempt_time);
        TextView attemptResult = view.findViewById(R.id.attempt_result);

        TestAttempt attempt = attempts.get(position);
        LocalDateTime dateTime = LocalDateTime.parse(attempt.getTestDate());
        attemptNumber.setText("Попытка № " + String.valueOf(position + 1));
        attemptDate.setText("Дата тестирования: " + dateTime.toLocalDate().toString());
        attemptTime.setText("Время тестирования: " + dateTime.toLocalTime().getHour() + ":" + dateTime.toLocalTime().getMinute());
        attemptResult.setText("Результат (в процентах): " + attempt.getPercentResult() + "%");

        return view;
    }
}
