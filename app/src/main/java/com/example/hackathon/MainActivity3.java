package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    TextView textView;
    String Final;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> consumption = new ArrayList<>();
    TextToSpeech textToSpeech;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.mic_speak_iv);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        String Theoretical = bundle.getString("Theoretical");
        String Practical = bundle.getString("Practical");
        name = bundle.getStringArrayList("name");
        consumption = bundle.getStringArrayList("consumption");

        String temp1 = "Theoretical power consumption: " + Theoretical + "kilo-watt hour & Practical  power consumption: " + Practical +" kilo-watt hour";
        String temp2 = ". List of appliances are: ";
        String temp3 = "";

        for (int i = 0; i<name.size();i++)
        {
            temp3 += name.get(i) + " " + consumption.get(i) + "kilo-watt hour. ";
        }

        Final = temp1+temp2+temp3;

        textView.setText(Final);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }

    private void speak()
    {
        imageView.setColorFilter(R.color.mic_enabled_color);
        textToSpeech.speak(Final,TextToSpeech.QUEUE_FLUSH,null);
    }
}