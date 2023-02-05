package ru.synergy.androidstartproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    private static final String LogcatTag = "Calculator_Activity";
    private static final String LifecycleTag = "LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LifecycleTag, "I'm on created, and i'm started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        final Button calculate = (Button) findViewById(R.id.calc);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LogcatTag, "Button have been pushed");
                calculateAnswer();
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

        TextView answer = (TextView) findViewById(R.id.result);

        Log.d(LogcatTag, "ALL view have been founded");

        float numone = 0;
        float numtwo = 0;
        String num1 = numOne.getText().toString();
        String num2 = numOne.getText().toString();
        if (num1.contentEquals("") && num1 != null) {
            numone = Integer.parseInt(numOne.getText().toString());
        }
        if (num1.contentEquals("") && num2!= null) {
            numtwo = Integer.parseInt(numTwo.getText().toString());
        }
        Log.d(LogcatTag, "Successfully grabbed data from input fields");
        Log.d(LogcatTag, "numone is:" + numone + " ; " + " numtwo is:" + numtwo);

        float solution = 0;


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