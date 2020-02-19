package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityRezult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezult);

        TextView resultLabel = (TextView)findViewById(R.id.rezultLabel);

        int score=getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);
        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);

        resultLabel.setText(score+" /50");

    }
}
