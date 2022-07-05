package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText e1, e2;
    private TextView t1;
    private String s1,s2;
    private boolean x = true;
    private Button add;
    private Button mul;
    private Button sub;
    private String error;

    public EditText getE1() {
        return e1;
    }

    public EditText getE2() {
        return e2;
    }

    private Button div;
    private Button power;
    private Button mod;

    public void checkInputs(){

    }

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public boolean checkError() {
        if(s1.equals(null) && s2.equals(null)){
            Toast.makeText(this, "Enter number 1 and number 2", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(s1.equals(null) && !s2.equals(null)){
            Toast.makeText(this, "Enter number 1", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(!s1.equals(null) && s2.equals(null)){
            Toast.makeText(this, "Enter number 2", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1= findViewById(R.id.num1);
        e2= findViewById(R.id.num2);
        t1= findViewById(R.id.result);
        add = findViewById(R.id.add);
        mul = findViewById(R.id.mul);
        sub = findViewById(R.id.sub);
        div = findViewById(R.id.div);
        power = findViewById(R.id.power);
        mod = findViewById(R.id.mod);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
                if(checkError()==true){
                    t1.setText(Double.parseDouble(s1)*Double.parseDouble(s2)+"");
                }
                else{
                    Toast.makeText(MainActivity.this, "sd", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();

            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();

            }
        });
    }
}