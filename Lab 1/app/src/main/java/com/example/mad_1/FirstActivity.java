package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mad_1.databinding.ActivityMainBinding;

public class FirstActivity extends AppCompatActivity {

    TextView txtvwAge;
    EditText edtName, edtYear;
    Button btnClick;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
//        setContentView(binding.getRoot());
        txtvwAge = (TextView) findViewById((R.id.txtvwAge));
        edtName = (EditText) findViewById(R.id.edtTxtName);
        edtYear = (EditText) findViewById(R.id.edtYear);
//        bin
    }

    public void fnGreet(View vw) {
//        String strName
        int year = Integer.parseInt(edtYear.getText().toString());
        int age = 2022 - year;
        txtvwAge.setText("Heloooo and welcome " + edtName.getText().toString() + ". You are " + age + " years old.");
    }

    public void fnThreadActivity(View view)
    {
        Intent intent = new Intent(this, ThreadedActivity.class);
//        String string = ((EditText) findViewById(R.id.txtFldName)).getText().toString();
        String string = ((EditText) findViewById(R.id.edtTxtName)).getText().toString();
        intent.putExtra("varStr1", string);
        startActivity(intent);
    }
}