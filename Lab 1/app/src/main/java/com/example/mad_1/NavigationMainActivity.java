package com.example.mad_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.example.mad_1.databinding.ActivityNavigationBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Vector;

public class NavigationMainActivity extends AppCompatActivity {

    private ActivityNavigationBinding binding;
    private Student student;

    private Vector<Student> students;
    private RecyclerView.Adapter adapter;

    private DatePickerDialog datePicker;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = binding.myDrawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = binding.navMenu;

        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.lab2:
                    intent = new Intent(this, ThreadedActivityMain.class);
                    startActivity(intent);
                    break;

                case R.id.lab4:
                    intent = new Intent(this, RegistrationActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab5:
                    intent = new Intent(this, StudentMainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab6:
                    intent = new Intent(this, NavigationMainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab7:
                    intent = new Intent(this, SecondActivityCam.class);
                    startActivity(intent);
                    break;

                case R.id.lab8:
                    intent = new Intent(this, GetRestActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab9:
                    intent = new Intent(this, AttendanceMainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab91:
                    intent = new Intent(this, SearchStudentActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        });

        binding.fabAdd.setOnClickListener(this::fnAdd);

        binding.edtBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                int mHour = cldr.get(Calendar.HOUR_OF_DAY);
                int mMinute = cldr.get(Calendar.MINUTE);
                String strDay ="";
                // date picker dialog
                datePicker = new DatePickerDialog(NavigationMainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                binding.edtBirthdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        students = new Vector<>();
        adapter = new StudentAdapter(getLayoutInflater(),students);

        binding.rcvStud.setAdapter(adapter);
        binding.rcvStud.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnAdd(View view)  {
        String fullname = binding.edtFullName.getText().toString();
        String studNo = binding.edtStudNum.getText().toString();
        String email = binding.edtEmail.getText().toString();
        String birth = binding.edtBirthdate.getText().toString();
        String gender = "";
        String state = binding.spnState.getSelectedItem().toString();

        if(binding.rbMale.isChecked())
            gender = binding.rbMale.getText().toString();
        else if(binding.rbFemale.isChecked())
            gender = binding.rbFemale.getText().toString();

        student = new Student(fullname,studNo,email,gender,birth, state);

        students.add(student);
        adapter.notifyItemInserted(students.size());
    }
}