package com.example.cryptoroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseCipher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cipher);
    }

    public void GoToVigenereCipher(View view) {
        Intent intent = new Intent(this , vigenere.class);
        startActivity(intent);
    }

    public void GoToAffineCipher(View view) {
        Intent intent = new Intent(this , affine.class);
        startActivity(intent);
    }

    public void GoToShiftCipher(View view) {
        Intent intent = new Intent(this , shift.class);
        startActivity(intent);
    }

    public void GoToSPNCipher(View view) {
        Intent intent = new Intent(this , Intro.class);
        startActivity(intent);
    }
}