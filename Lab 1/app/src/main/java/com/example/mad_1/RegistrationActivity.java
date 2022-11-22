package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.mad_1.databinding.ActivityRegistrationBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    DatePickerDialog datePicker;
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registration);

        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fabAddUser.setEnabled(false);



        if (binding.edtBirthdate.getText().toString().trim().length() >= 0) {
            binding.edtBirthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    fnInvokeDatePicker();
                }
            });
        } else {
            binding.edtBirthdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fnInvokeDatePicker();
                }
            });
        }



        binding.fabAddUser.setOnClickListener(this::fnAddUser);

        binding.rbMale.setChecked(true);

        ArrayList<EditText> inputs = new ArrayList<EditText>(Arrays.asList(binding.edtFullName, binding.edtPwd,
                binding.edtBirthdate, binding.edtEmail, binding.edtAddress));

        for (EditText input : inputs) {
            input.addTextChangedListener(textWatcher);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            CharSequence cs1 = binding.edtFullName.getText().toString().trim();
            CharSequence cs2 = binding.edtPwd.getText().toString().trim();
            CharSequence cs3 = binding.edtBirthdate.getText().toString().trim();
            CharSequence cs4 = binding.edtEmail.getText().toString().trim();
            CharSequence cs5 = binding.edtAddress.getText().toString().trim();

            if (cs1.length() > 0 && cs2.length() > 0 && cs3.length() > 0 && cs4.length() > 0 && cs5.length() > 0 && (binding.rbFemale.isChecked() || binding.rbMale.isChecked()))
                binding.fabAddUser.setEnabled(true);
            else
                binding.fabAddUser.setEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void fnInvokeDatePicker() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        datePicker = new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                binding.edtBirthdate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        datePicker.show();
    }

    private void fnAddUser(View view) {
        String strFullName = binding.edtFullName.getText().toString();
        String strPwd = binding.edtPwd.getText().toString();
        String strEmail = binding.edtEmail.getText().toString();
        String strBirth = binding.edtBirthdate.getText().toString();
        String strAddress = binding.edtAddress.getText().toString();
        String strGender = "";

        if (binding.rbMale.isChecked())
            strGender = binding.rbMale.getText().toString();
        else if (binding.rbFemale.isChecked())
            strGender = binding.rbFemale.getText().toString();

        User user = new User(strFullName, strPwd, strEmail, strBirth, strGender, strAddress);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("objUser", user);

        startActivity(intent);
    }
}