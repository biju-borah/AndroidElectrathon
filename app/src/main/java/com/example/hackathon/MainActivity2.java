package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    LinearLayout layoutList;
    Button buttonAdd;
    Button buttonSubmitList;
    float calculate = 0f;
    EditText E1,E2;
    String Calculate;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> consumption = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonSubmitList = findViewById(R.id.b2);
        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        E1 = findViewById(R.id.e1);
        E2 = findViewById(R.id.e2);

        buttonAdd.setOnClickListener(this);
        buttonSubmitList.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_add:

                addView();

                break;

            case R.id.b2:

                if(checkIfValidAndRead()){
                    cal();
                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                    Bundle bundle = new Bundle();
                    String result = Float.toString(calculate);
                    bundle.putStringArrayList("name",name);
                    bundle.putStringArrayList("consumption",consumption);
                    bundle.putString("Practical",getCalculate());
                    bundle.putString("Theoretical",result);
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
            EditText e4 = (EditText)view.findViewById(R.id.edit_name);

            if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty() || e3.getText().toString().isEmpty() || e4.getText().toString().isEmpty())
            {
                result = false;
                break;
            }

            int q = Integer.parseInt(e1.getText().toString());
            float d = Float.parseFloat(e2.getText().toString());
            int r = Integer.parseInt(e3.getText().toString());

            calculate += ((q * d * r) * 30)/1000;
            float temp = (q*d*r*30)/100;
            String T = Float.toString(temp);
            consumption.add(T);
            name.add(e4.getText().toString());


        }
        if(!result) {
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }
        if(E1.getText().toString().isEmpty() || E2.getText().toString().isEmpty() )
        {
            result = false;
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();

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
    private void cal(){
        float e1 = Float.parseFloat(E1.getText().toString());
        float e2 = Float.parseFloat(E2.getText().toString());
        float temp = e2-e1;
        String fuck = Float.toString(temp);
        setCalculate(fuck);

    }
    public void setCalculate(String calculate){
        Calculate = calculate;
    }
    public String getCalculate()
    {
        return Calculate;
    }
}