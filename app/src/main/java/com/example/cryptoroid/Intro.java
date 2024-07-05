package com.example.cryptoroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Intro extends AppCompatActivity {

        Button B1 , B2 , B3;
        public String []KeysArray;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intro);

            B1 = findViewById(R.id.EncryptionButton);
            B2 = findViewById(R.id.DecryptionButton);
            B3 = findViewById(R.id.KeysButton);


        }


        public void encryptBtn(View view) {

            Intent intent = new Intent(this, Encryption.class);
            intent.putExtra("Keys" , KeysArray);
            startActivity(intent);


        }

        public void decryptBtn(View view) {
            Intent intent = new Intent(this, decryption.class);
            intent.putExtra("Keys" , KeysArray);
            startActivity(intent);
        }


    public void keysBtn(View view) {

            int requestCode = 1;

            Intent intent = new Intent(this , Keys.class);
            startActivityForResult(intent , requestCode);
    }

    @Override
    protected void onActivityResult
            (int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
                    KeysArray = data.getExtras().getStringArray("Keys");
                    B1.setEnabled(true);
                    B2.setEnabled(true);
        }

    }


}