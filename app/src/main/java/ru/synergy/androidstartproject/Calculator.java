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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private void calculateAnswer() {
        EditText numOne = (EditText) findViewById(R.id.editTextNumberDecimal);
        EditText numTwo = (EditText) findViewById(R.id.editTextNumberDecimal2);

        RadioButton add = (RadioButton) findViewById(R.id.add);
        RadioButton sub = (RadioButton) findViewById(R.id.subtract);
        RadioButton multiple = (RadioButton) findViewById(R.id.multiple);
        RadioButton divide = (RadioButton) findViewById(R.id.divide);

        TextView answer = (TextView) findViewById(R.id.result);

        Log.d(LogcatTag, "ALL view have been founded");

        float numone = Integer.parseInt(numOne.getText().toString());
        float numtwo = Integer.parseInt(numTwo.getText().toString());

        Log.d(LogcatTag, "Successfully grabbed data from input fields");
        Log.d(LogcatTag, "numone is:" + numone + " ; " + " numtwo is:" + numtwo );

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
        Log.d(LogcatTag,"the result of operation is: " + solution);

        //Log.wtf() // What a Terrible Failure == error


        answer.setText("The answer is" + solution);
    }
}