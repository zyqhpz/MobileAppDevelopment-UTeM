package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mad_1.databinding.ActivityRegistrationBinding;

public class SecondActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        setContentView(R.layout.activity_second);
        Intent intent = new Intent();
        User user = (User) getIntent().getSerializableExtra("objUser");

        TextView textFull = (TextView) findViewById(R.id.textFullName);
        textFull.setText("Full name: " + user.getFullname());
    }
}