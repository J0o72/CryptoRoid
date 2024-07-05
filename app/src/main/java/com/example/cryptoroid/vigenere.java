package com.example.cryptoroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class vigenere extends AppCompatActivity {

    TextInputEditText Key1 , Key2 , Key3 , Key4 , Key5 , Text;
    Button encryptbtn , decryptbtn , copybtn ;
    TextView corrText;

    public static char character[] = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h', 'i' , 'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' ,
            'q' , 'r' , 's' , 't' ,'u','v' , 'w' , 'x' , 'y' , 'z' , 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J',
            'K' , 'L' , 'M' , 'N' , 'O' , 'P', 'Q', 'R' , 'S' , 'T','U' , 'V' , 'W' ,'X' , 'Y' , 'Z' , ' '} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere);

        Key1 = findViewById(R.id.textInputEditTextt);
        Key2 = findViewById(R.id.textInputEditTexttt);
        Key3 = findViewById(R.id.textInputEditTextt3);
        Key4 = findViewById(R.id.textInputEditTextt4);
        Key5 = findViewById(R.id.textInputEditTextt5);
        Text = findViewById(R.id.textInputEditTextt6);


        encryptbtn = findViewById(R.id.button8);
        decryptbtn = findViewById(R.id.buttonn);
        copybtn = findViewById(R.id.buttonn2);

        corrText = findViewById(R.id.textVieww);

    }

    public static int getindex(char x)
    {
        int index = 0 ;
        for(int i = 0 ; i < character.length ; i++)
        {
            if (x == character[i])
                index = i ;
        }
        return index ;
    }


    public  static String encryption(String m , int []keys)
    {
        int index = 0 ;
        String s = "" ;
        int v;
        int len;

        if(m.length() % 5 == 0 ){
            len = m.length() / 5 ;
        }
        else
            len = (m.length() / 5) + 1;

        for(int i = 0 ; i < len ; i++)
        {
            for(int j = 0; j < keys.length ; j++)
            {
                if(index < m.length())
                {
                    v = getindex(m.charAt(index++)) + keys[j] ;
                    if(v < 0)
                    {
                        v = (v + character.length) % character.length ;
                        while(v < 0)
                        {
                            v = (v + character.length) % character.length ;
                        }
                    }
                    else
                        v = v % character.length ;
                    s += character[v] ;
                }
            }
        }


        return s;
    }


    public  static String decryption(String m , int []keys)
    {
        int index = 0 ;
        String s = "" ;
        int v ;

        int len;

        if(m.length() % 5 == 0 ){
            len = m.length() / 5 ;
        }
        else
            len = (m.length() / 5) + 1;

        for(int i = 0 ; i < len ; i++)
        {

            for(int j = 0; j < keys.length ; j++)
            {
                if(index < m.length())
                {
                    v = getindex(m.charAt(index++)) - keys[j];
                    if(v < 0)
                    {
                        v = (v + character.length) % character.length ;
                        while (v < 0)
                        {
                            v = (v + character.length) % character.length ;
                        }
                    }
                    else
                        v = v % character.length ;

                    s += character[v] ;
                }
            }
        }


        return s;
    }


    public void viencrypt(View view) {

        int key1 = Integer.parseInt(Key1.getText().toString());
        int key2 = Integer.parseInt(Key2.getText().toString());
        int key3 = Integer.parseInt(Key3.getText().toString());
        int key4 = Integer.parseInt(Key4.getText().toString());
        int key5 = Integer.parseInt(Key5.getText().toString());
        String text = Text.getText().toString();
        int []Keys = {key1 , key2 , key3 , key4 , key5};

        corrText.setText(encryption(text , Keys));


    }

    public void videcrypt(View view) {

        int key1 = Integer.parseInt(Key1.getText().toString());
        int key2 = Integer.parseInt(Key2.getText().toString());
        int key3 = Integer.parseInt(Key3.getText().toString());
        int key4 = Integer.parseInt(Key4.getText().toString());
        int key5 = Integer.parseInt(Key5.getText().toString());
        String text = Text.getText().toString();
        int []Keys = {key1 , key2 , key3 , key4 , key5};

        corrText.setText(decryption(text , Keys));

    }

    public void viCopy(View view) {

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("CorrText" , corrText.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this , "Text Copied" , Toast.LENGTH_LONG).show();

    }
}