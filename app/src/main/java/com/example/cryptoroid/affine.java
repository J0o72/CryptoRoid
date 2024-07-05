package com.example.cryptoroid;

import static android.graphics.Color.rgb;

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

public class affine extends AppCompatActivity {

    TextInputEditText Key1 , Key2 , Text;
    Button encryptbtn , decryptbtn , copybtn ;
    TextView corrText;

    public static char character[] = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h', 'i' , 'j' , 'k' , 'l' , 'm' , 'n' , 'o' , 'p' ,
            'q' , 'r' , 's' , 't' ,'u' ,'v' , 'w' , 'x' , 'y' , 'z' , 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J',
            'K' , 'L' , 'M' , 'N' , 'O' , 'P', 'Q', 'R' , 'S' , 'T' , 'U' , 'W','V' ,'X' , 'Y' , 'Z' , ' ' } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affine);

        Key1 = findViewById(R.id.textInputEditTextt);
        Key2 = findViewById(R.id.textInputEditTextt2);
        Text = findViewById(R.id.textInputEditTextt3);

        encryptbtn = findViewById(R.id.button8);
        decryptbtn = findViewById(R.id.buttonn);
        copybtn = findViewById(R.id.buttonn2);

        corrText = findViewById(R.id.textVieww);
    }



    public static long GCD(int a , int b)
    {
        int c ;
        while(b != 0)
        {
            c = a % b ;
            a = b ;
            b = c ;
        }
        return a ;
    }

    public static int getinverse(int a)
    {
        int digit = 1 ;
        while(true)
        {
            if((a * digit) % 53 == 1 )
                return digit ;
            digit++ ;
        }
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

    public static String encryption(String m ,int x , int y)
    {
        char ch[] = m.toCharArray();
        int ch2[] = new int[ch.length];
        String s = "" ;

        if(GCD(x,53) == 1)
        {
            for(int i = 0 ; i < ch.length ; i++)
            {
                ch2[i] =  (x * getindex(ch[i]) + y) % 53 ;
                s += character[ch2[i]] ;
            }
        }
        else
            return "Error , Key1 Has No Inverse" ;

        return s ;
    }

    public static String decryptoin(String m , int x , int y)
    {
        char ch[] = m.toCharArray();
        int ch2[] = new int[ch.length] ;
        String s = "" ;
        int z ;
        if(GCD(x,53) == 1)
        {
            for(int i = 0 ; i < ch.length ; i++)
            {
                z = ( getinverse(x) * ( getindex(ch[i] ) - y ) ) ;
                if(z < 0)
                {
                    z = (z+53)% 53 ;
                    while(z < 0)
                    {
                        z = (z+53)% 53 ;
                    }
                }
                else
                    z = z % 53 ;
                ch2[i] = z ;
                s += character[ch2[i]] ;
            }
        }
        else
            return "Error , Key1 Has No Inverse" ;
        return s ;
    }




    public void Affencrypt(View view) {

        String text = Text.getText().toString();
        int key1 = Integer.parseInt(String.valueOf(Key1.getText().toString()));
        int key2 = Integer.parseInt(String.valueOf(Key2.getText().toString()));
        corrText.setText(encryption(text , key1 , key2));

    }

    public void Affdecrypt(View view) {
        String text = Text.getText().toString();
        int key1 = Integer.parseInt(String.valueOf(Key1.getText().toString()));
        int key2 = Integer.parseInt(String.valueOf(Key2.getText().toString()));
        corrText.setText(decryptoin(text, key1, key2));
    }

    public void affCopy(View view) {

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("CorrText" , corrText.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this , "Text Copied" , Toast.LENGTH_LONG).show();

    }
}