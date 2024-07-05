package com.example.cryptoroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class Keys extends AppCompatActivity {


    TextInputEditText et1 , et2 , et3 , et4 , et5 , et6 , et7 , et8 , et9 , et10;
    Button B1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keys);

        et1 = findViewById(R.id.textInputEditText1);
        et2 = findViewById(R.id.textInputEditText2);
        et3 = findViewById(R.id.textInputEditText3);
        et4 = findViewById(R.id.textInputEditText4);
        et5 = findViewById(R.id.textInputEditText5);
        et6 = findViewById(R.id.textInputEditText6);
        et7 = findViewById(R.id.textInputEditText7);
        et8 = findViewById(R.id.textInputEditText8);
        et9 = findViewById(R.id.textInputEditText9);
        et10 = findViewById(R.id.textInputEditText10);
        B1 = findViewById(R.id.button3);



    }


    public void backToHome(View view) {

        String []Keys = {et1.getText().toString() , et2.getText().toString() ,et3.getText().toString() , et4.getText().toString()
                , et5.getText().toString() , et6.getText().toString() , et7.getText().toString() , et8.getText().toString()
                , et9.getText().toString() ,et10.getText().toString()
        };

        Intent intent = new Intent();
        intent.putExtra("Keys" , Keys);
        setResult(RESULT_OK , intent);
        finish();

    }
}