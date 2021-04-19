package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    float cost;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("Result");
        float temp = Float.parseFloat(result);
        cost = 5.33f * temp;
        String finalShow = "Total energy consumption:" + result + " kilo-watt hour. Approx cost of electricity in india is 5.33 rupees per kilo-watt hour. So estimated bill is " + cost + "/-";
        textView.setText(finalShow);
    }
}