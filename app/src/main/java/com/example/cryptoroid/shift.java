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

public class shift extends AppCompatActivity {

    TextInputEditText Key1 , Text ;
    Button encryptbtn , decryptbtn , copybtn ;
    TextView corrText;

    public static char character[] = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h', 'i' , 'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' ,
            'q' , 'r' , 's' , 't', 'u' ,'v' , 'w' , 'x' , 'y' , 'z' , 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J',
            'K' , 'L' , 'M' , 'N' , 'O' , 'P', 'Q', 'R' , 'S' , 'T' , 'U' , 'W','V' ,'X' , 'Y' , 'Z' , ' ' } ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift);

        Key1 = findViewById(R.id.textInputEditTextt);
        Text = findViewById(R.id.textInputEditTextt3);

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


    public static String encryption(String m , int key)
    {
        int ch[] = new int[m.length()];
        int index;
        String s = "" ;
        for(int i = 0 ; i < ch.length ; i++)
        {
            index = getindex(m.charAt(i))+ key ;
            if(index < 0)
            {
                index = (index + 53) % 53 ;
                while(index < 0)
                {
                    index = (index + 53) % 53 ;
                }
            }
            else
                index = index % 53 ;
            s += character[index] ;

        }
        return s ;
    }


    public static String decryption(String m , int key)
    {
        int ch[] = new int[m.length()];
        int index;
        String s = "" ;
        for(int i = 0 ; i < ch.length ; i++)
        {
            index = getindex(m.charAt(i))- key ;
            if(index < 0)
            {
                index = (index + 53) % 53 ;
                while(index < 0)
                {
                    index = (index + 53) % 53 ;
                }
            }
            else
                index = index % 53 ;
            s += character[index] ;

        }
        return s ;

    }


    public void shiftencrypt(View view) {

        String text = Text.getText().toString();
        int key1 = Integer.parseInt(String.valueOf(Key1.getText().toString()));
        corrText.setText(encryption(text, key1));

    }

    public void shiftdecrypt(View view) {

        String text = Text.getText().toString();
        int key1 = Integer.parseInt(String.valueOf(Key1.getText().toString()));
        corrText.setText(decryption(text, key1));
    }

    public void shiftCopy(View view) {

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("CorrText" , corrText.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this , "Text Copied" , Toast.LENGTH_LONG).show();

    }
}