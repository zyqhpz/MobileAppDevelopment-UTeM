package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

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

        Button btnHello = (Button) findViewById(R.id.btnHello);
        btnHello.setText(getString(R.string.btnHello));

        txtHello = findViewById(R.id.txtHello);
        editText1 = findViewById(R.id.editText1);



        binding.btnHello.setOnClickListener(this::fnChangeText);
    }

    public void fnChangeText(View view) {
//        binding.txtHello.setText("Heyy");
        txtHello.setText(txtHello.getText().toString() + " " + editText1.getText().toString());
    }
}