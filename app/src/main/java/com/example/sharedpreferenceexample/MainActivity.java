package com.example.sharedpreferenceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnSave, btnLoud;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "save text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.etText);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoud = (Button) findViewById(R.id.btnLoud);

//        btnSave.setOnClickListener(this);
//        btnLoud.setOnClickListener(this);

        LoadText();

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                saveText();
                Log.d(SAVED_TEXT,et.getText().toString());
                return true;
            }
        });
    }



//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnSave:
//                saveText();
//                break;
//            case R.id.btnLoud:
//                LoadText();
//                break;
//            default:
//                break;
//        }
//    }

    private void LoadText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedText = sharedPreferences.getString(SAVED_TEXT, "Ничего не сохранено в шаред преференсес");
    et.setText(savedText);
        Toast.makeText(this, "Text louded", Toast.LENGTH_LONG).show();
    }


    private void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVED_TEXT,et.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}