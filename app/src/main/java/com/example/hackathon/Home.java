package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener{

    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmitList;
    float calculate = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitList = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSubmitList.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_add:

                addView();

                break;

            case R.id.button_submit_list:

                if(checkIfValidAndRead()){

                    Intent intent = new Intent(Home.this,Result.class);
                    Bundle bundle = new Bundle();
                    String result = Float.toString(calculate);
                    bundle.putString("Result",result);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                break;

        }


    }
    private boolean checkIfValidAndRead(){
        boolean result = true;
        for (int i=0;i<layoutList.getChildCount();i++)
        {
            View view = layoutList.getChildAt(i);
            EditText e1 = (EditText)view.findViewById(R.id.quantity);
            EditText e2 = (EditText)view.findViewById(R.id.duration);
            EditText e3 = (EditText)view.findViewById(R.id.rating);

            int q = Integer.parseInt(e1.getText().toString());
            float d = Float.parseFloat(e2.getText().toString());
            int r = Integer.parseInt(e3.getText().toString());

            calculate += ((q * d * r) * 30)/1000;


        }
        return result;
    }
    private void addView() {

        final View view = getLayoutInflater().inflate(R.layout.row_add,null,false);

        EditText editText = (EditText)view.findViewById(R.id.edit_name);
        EditText editText2 = (EditText)view.findViewById(R.id.quantity);
        EditText editText3 = (EditText)view.findViewById(R.id.duration);
        EditText editText4 = (EditText)view.findViewById(R.id.rating);
        ImageView imageClose = (ImageView)view.findViewById(R.id.image_remove);


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(view);
            }
        });

        layoutList.addView(view);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}