package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mad_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    TextView txtHello;
    EditText editText1;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

//        Button btnHello = (Button) findViewById(R.id.btnHello);
//        btnHello.setText(getString(R.string.btnHello));
//
//        txtHello = findViewById(R.id.txtHello);
//        editText1 = findViewById(R.id.editText1);
//
//
//
//        binding.btnHello.setOnClickListener(this::fnChangeText);

        Button btnLab2 = (Button) findViewById(R.id.btnLab2);
        btnLab2.setOnClickListener(this::changeActivityTwo);

        Button btnLab4 = (Button) findViewById(R.id.btnLab4);
        btnLab4.setOnClickListener(this::changeActivityFour);

        Button btnLab5 = (Button) findViewById(R.id.btnLab5);
        btnLab5.setOnClickListener(this::changeActivityFive);

        Button btnLab6 = (Button) findViewById(R.id.btnLab6);
        btnLab6.setOnClickListener(this::changeActivitySix);

        Button btnLab7 = (Button) findViewById(R.id.btnLab7);
        btnLab7.setOnClickListener(this::changeActivitySeven);
    }

    public void fnChangeText(View view) {
//        binding.txtHello.setText("Heyy");
        txtHello.setText(txtHello.getText().toString() + " " + editText1.getText().toString());
    }

    public void changeActivityTwo(View view) {
        Intent intent = new Intent(this, ThreadedActivityMain.class);
        startActivity(intent);
    }

    public void changeActivityFour(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void changeActivityFive(View view) {
        Intent intent = new Intent(this, StudentMainActivity.class);
        startActivity(intent);
    }

    public void changeActivitySix(View view) {
        Intent intent = new Intent(this, NavigationMainActivity.class);
        startActivity(intent);
    }

    public void changeActivitySeven(View view) {
        Intent intent = new Intent(this, SecondActivityCam.class);
        startActivity(intent);
    }
}