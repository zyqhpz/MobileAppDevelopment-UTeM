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
}