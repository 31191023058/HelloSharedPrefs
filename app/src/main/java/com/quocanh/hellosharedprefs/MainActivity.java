package com.quocanh.hellosharedprefs;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int count = 0;
    int color;
    Button blackButton, redButton, blueButton, greenButton, countButton, resetButton;
    TextView countTextView;
    SharedPreferences sharedPreferences;
    String sharedPrefsFile = "com.quocanh.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTextView = findViewById(R.id.countTextView);
        color = getResources().getColor(R.color.color_default);

        sharedPreferences = getSharedPreferences(sharedPrefsFile, MODE_PRIVATE);

        count = sharedPreferences.getInt("count", 0);
        countTextView.setText(String.valueOf(count));
        color = sharedPreferences.getInt("color", color);
        countTextView.setBackgroundColor(color);

        blackButton = findViewById(R.id.buttonBlack);
        blackButton.setOnClickListener(this);
        redButton = findViewById(R.id.buttonRed);
        redButton.setOnClickListener(this);
        blueButton = findViewById(R.id.buttonBlue);
        blueButton.setOnClickListener(this);
        greenButton = findViewById(R.id.buttonGreen);
        greenButton.setOnClickListener(this);
        countButton = findViewById(R.id.countButton);
        countButton.setOnClickListener(this);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
    }

    public void changeBackgroundColor (int newColor) {
        countTextView.setBackgroundColor(newColor);
        color = newColor;
    }

    public void countUp () {
        count++;
        countTextView.setText(String.valueOf(count));
    }

    public void reset () {
        count = 0;
        color = getResources().getColor(R.color.color_default);

        countTextView.setText(String.valueOf(count));
        countTextView.setBackgroundColor(color);

        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putInt("count", count);
        preferencesEditor.putInt("color", color);
        preferencesEditor.apply();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBlack:
                changeBackgroundColor(getResources().getColor(R.color.black));
                break;
            case R.id.buttonRed:
                changeBackgroundColor(getResources().getColor(R.color.red));
                break;
            case R.id.buttonBlue:
                changeBackgroundColor(getResources().getColor(R.color.blue));
                break;
            case R.id.buttonGreen:
                changeBackgroundColor(getResources().getColor(R.color.green));
                break;
            case R.id.countButton:
                countUp();
                break;
            case R.id.resetButton:
                reset();
                break;
        }
    }
}