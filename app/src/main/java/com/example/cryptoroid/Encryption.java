package com.example.cryptoroid;

import static android.graphics.Color.rgb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Encryption extends AppCompatActivity {

    TextInputEditText Et;
    Button B1 , B2 ;
    TextView tv;
    String []KeysArray;
    String plaintext;
    public static int []sbox1 = new int[256];

    public void ss(){
        for(int i = 0 ; i <= 255 ; i++){
            sbox1[i] = i;
        }
    }
    public static int []sbox2 = {122, 235, 31, 111, 231, 51, 165, 210, 172,
            219, 182, 168, 66, 34, 52, 237, 40, 163, 97,
            120, 109, 242, 62, 139, 24, 117, 175, 198,
            195, 243, 207, 16, 81, 29, 49, 100, 113, 84,
            200, 132, 39, 96, 136, 202, 223, 5, 64, 114,
            180, 151, 184, 176, 123, 59, 146, 194, 36, 255,
            199, 50, 211, 61, 141, 93, 174, 228, 167, 23, 251,
            203, 230, 101, 108, 252, 95, 190, 247, 69, 92, 156,
            135, 65, 226, 130, 47, 192, 238, 70, 3, 82, 154, 54,
            150, 14, 220, 13, 137, 19, 171, 68, 227, 90, 53, 60,
            118, 233, 234, 144, 177, 75, 115, 133, 89, 35, 250, 209,
            6, 134, 105, 21, 15, 159, 224, 57, 205, 25, 239, 7, 107,
            214, 28, 158, 79, 32, 188, 160, 73, 11, 218, 126, 253,
            48, 42, 178, 18, 41, 254, 45, 153, 217, 246, 80, 145,
            85, 78, 44, 94, 179, 249, 56, 204, 43, 213, 201, 99, 138,
            149, 86, 142, 129, 206, 125, 140, 236, 208, 2, 58, 189, 98,
            30, 76, 71, 106, 91, 77, 104, 72, 8, 10, 1, 148, 67, 241, 169,
            166, 170, 240, 112, 127, 232, 161, 157, 183, 46, 22, 212, 12,
            20, 191, 0, 103, 216, 33, 229, 221, 244, 248, 187, 225, 181,
            74, 26, 4, 119, 124, 215, 162, 147, 88, 186, 83, 197, 143, 193,
            164, 116, 63, 87, 222, 121, 131, 38, 185, 17, 102, 152, 155, 128,
            27, 245, 9, 37, 173, 196, 110, 55};

    char []btt;
    public char[] XOR(String s1 , String s2){
        btt = new char[s2.length()];

        for(int i = 0 ; i < btt.length ; i++)
            btt[i] =  (char) (s1.charAt(i) ^ s2.charAt(i));

        return btt;
    }

    public static char[] substitution(char[]b ){

        char[]ss=new char[b.length];

        for(int i = 0 ; i < ss.length ; i++){

            ss[i]=(char) sbox2[b[i]];

        }

        return ss;
    }

    public int[] tobinary(int a){

        int[] b = new int[8];
        for(int i = 0 ; a > 0 ; i++){
            b[i] = a % 2;
            a = a / 2;

        }
        return b;
    }

    public static int[] per8bits(int[] b){
        int []ss=new int[8];
        ss[0]=b[5];
        ss[1]=b[4];
        ss[2]=b[6];
        ss[3]=b[0];
        ss[4]=b[7];
        ss[5]=b[1];
        ss[6]=b[3];
        ss[7]=b[2];

        return ss;
    }

    public String permutation(char[] s){

        int[][] bin = new int[s.length][8];

        for (int i = 0 ; i < bin.length ; i++) {
            bin[i] = tobinary(s[i]);
        }


        int[][] perm = new int[bin.length][8];

        for (int i = 0 ; i < perm.length ; i++) {
            perm[i] = per8bits(bin[i]);
        }

        String a="";

        for (int i = 0 ; i < perm.length ; i++) {
            String d = "";
            for (int j =  perm[i].length-1 ; j >= 0 ; j--) {
                d += perm[i][j];

            }
            int decimal = Integer.parseInt(d, 2);
            a += (char) decimal;
        }
        return  a;
    }


    public String tenRounds(String []KeysArr, String plaintxt){

        for(int i = 0 ; i < 9 ; i++){

            char[] ar = XOR(KeysArr[i] , plaintxt);
            char[] s = substitution(ar);
            plaintxt = permutation(s);

        }

        char[] ar = XOR(KeysArr[9] , plaintxt);
        char[] s = substitution(ar);
        String en= new String(s);

        return en;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);


        Et = findViewById(R.id.textInputEditText1);
        B1 = findViewById(R.id.button);
        B2 = findViewById(R.id.button2);
        tv = findViewById(R.id.textView);

        Et.addTextChangedListener(TxtW);

        Intent in = getIntent();
        KeysArray = in.getExtras().getStringArray("Keys");


    }

    public void EncryptCopy(View view) {
        if(tv.getText().toString() != ""){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("cipher" , tv.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this , "Text Copied" , Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this , "No Text Copied" , Toast.LENGTH_LONG).show();
        }

    }

    public void encrypt(View view) {

        plaintext = Et.getText().toString();

        while(plaintext.length() % KeysArray[0].length() != 0)
            plaintext += ' ';

        int flag = 0;
        for(int i = 0 ; i < KeysArray.length ; i++){
            if(KeysArray[i].length() == plaintext.length()){
                flag = 1;
            }
            else {
                    flag = 0;
                    tv.setTextColor(rgb(255,0,0));
                    tv.setText("The PlainText And Keys Are Not The Same Lenght");
                    break;
            }

        }

        if(flag == 1) {
            String cipherText = tenRounds(KeysArray, plaintext);
            tv.setTextColor(rgb(255,255,255));
            tv.setText(cipherText);
        }
    }

    TextWatcher TxtW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            B1.setEnabled(!(Et.getText().toString().trim().isEmpty()));

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}