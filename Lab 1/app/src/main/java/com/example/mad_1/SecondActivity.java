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

        TextView textFullname = (TextView) findViewById(R.id.textFullName);
        textFullname.setText("Full name: " + user.getFullname());

        TextView textPassword = (TextView) findViewById(R.id.textPassword);
        textPassword.setText("Password: " + user.getPwd());

        TextView textEmail = (TextView) findViewById(R.id.textEmail);
        textEmail.setText("Email: " + user.getEmail());

        TextView textBirthdate = (TextView) findViewById(R.id.textBirthdate);
        textBirthdate.setText("Birthdate: " + user.getBirthdate());

        TextView textAddress = (TextView) findViewById(R.id.textAddress);
        textAddress.setText("Address: " + user.getAddress());

        TextView textGender = (TextView) findViewById(R.id.textGender);
        textGender.setText("Gender: " + user.getGender());

    }
}