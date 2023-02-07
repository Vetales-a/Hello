package ru.synergy.androidstartproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Calculator extends AppCompatActivity {

    private static final String LogcatTag = "Calculator_Activity";
    private static final String LifecycleTag = "LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LifecycleTag, "I'm on created, and i'm started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        final Button calculate = (Button) findViewById(R.id.calc);

//        // Context training
//        TextView textView = new TextView(this );
//        ListAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),);
//        //  Доступ из класса Activity -- наследник ConText
//        getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        // Shared prefs доступ с использованием контекста приложения.
//        SharedPreferences prefs = getApplicationContext().getSharedPreferences("PREFS",MODE_PRIVATE);
//
        //////
        /////  intent - посылка



        //
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LogcatTag, "Button have been pushed");
                calculateAnswer();
                Intent i = new Intent(Calculator.this , MainActivity.class);/// Напсать письмо
//                startActivity(i);/// Отправить его
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LifecycleTag, "I'm onStart(), and i'm started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LifecycleTag, "I'm onStart(), and i'm started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LifecycleTag, "I'm onDestroy(), and i'm started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LifecycleTag, "I'm onPause(), and i'm started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LifecycleTag, "I'm onResume(), and i'm started");
    }

    private void calculateAnswer() {
        EditText numOne = (EditText) findViewById(R.id.editTextNumberDecimal);
        EditText numTwo = (EditText) findViewById(R.id.editTextNumberDecimal2);

        RadioButton add = (RadioButton) findViewById(R.id.add);
        RadioButton sub = (RadioButton) findViewById(R.id.subtract);
        RadioButton multiple = (RadioButton) findViewById(R.id.multiple);
        RadioButton divide = (RadioButton) findViewById(R.id.divide);

//        numOne.setText("0");
//        numTwo.setText("0");
//        add.setChecked(true);

        TextView answer = (TextView) findViewById(R.id.result);

        Log.d(LogcatTag, "ALL view have been founded");

//        try {
//            int a = 25 / 0;
//        } catch (ArithmeticException e){
//            e.printStackTrace();
//        }

        float numone = 0;
        float numtwo = 0;
        String num1 = numOne.getText().toString();
        String num2 = numTwo.getText().toString();
        if (!num1.equals("") && num1 != null) {
            numone = Integer.parseInt(numOne.getText().toString());
        }
        if (!num2.equals("") && num2!= null) {
            numtwo = Integer.parseInt(numTwo.getText().toString());
        }
        Log.d(LogcatTag, "Successfully grabbed data from input fields");
        Log.d(LogcatTag, "numone is:" + numone + " ; " + " numtwo is:" + numtwo);

        float solution = 0;

        if (numtwo == 0) {
            Toast.makeText(this, "Number two Cannot be zero", Toast.LENGTH_SHORT).show();
            return;
        }


        if (add.isChecked()) {
            Log.d(LogcatTag, "Operation is add");
            solution = numone + numtwo;
        }


        if (sub.isChecked()) {
            Log.d(LogcatTag, "Operation is sub");
            solution = numone - numtwo;
        }

        if (multiple.isChecked()) {
            Log.d(LogcatTag, "Operation is multiple");
            solution = numone * numtwo;
        }

        if (divide.isChecked()) {
            Log.d(LogcatTag, "Operation is divide");
            if (numtwo == 0) {
                Toast.makeText(this, "Number two Cannot be zero", Toast.LENGTH_SHORT).show();
                return;
            }
            solution = numone / numtwo;
        }
        Log.d(LogcatTag, "the result of operation is: " + solution);

        //Log.wtf() // What a Terrible Failure == error


        answer.setText("The answer is" + solution);

    }
}