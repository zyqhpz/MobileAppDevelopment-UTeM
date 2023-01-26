package com.example.mad_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mad_1.databinding.ActivityAttendanceMainBinding;
import com.example.mad_1.databinding.ActivityStudentMainBinding;
import com.example.mad_1.sqlite.StudentsDB;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AttendanceMainActivity extends AppCompatActivity {

    private ActivityAttendanceMainBinding binding;
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
        setContentView(R.layout.activity_student_main);

        binding = ActivityAttendanceMainBinding.inflate(getLayoutInflater());

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

        binding.fabAdd.setOnClickListener(this::fnAddToRest);

        binding.edtBirthdate.setOnClickListener(view -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);

            int mHour = cldr.get(Calendar.HOUR_OF_DAY);
            int mMinute = cldr.get(Calendar.MINUTE);
            String strDay ="";
            // date picker dialog
            datePicker = new DatePickerDialog(AttendanceMainActivity.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> binding.edtBirthdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            datePicker.show();
        });

        students = new Vector<>();
        adapter = new StudentAdapter(getLayoutInflater(),students);

        binding.rcvStud.setAdapter(adapter);
        binding.rcvStud.setLayoutManager(new LinearLayoutManager(this));

        // retrieve students data
        boolean success = false;
        try {
            fnGetAllFromRest();
            adapter.notifyDataSetChanged();
            Log.e("data from server", students.toString());
            success = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "failed to retrieve from remote", Toast.LENGTH_SHORT).show();
        } finally {
            if (!success) {
                try {
                    StudentsDB studentsDB = new StudentsDB(this);
                    students.addAll(studentsDB.fnGetAllStudents());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "failed to retrieve from local", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnAdd()  {
        String fullname = binding.edtFullName.getText().toString();
        String studNo = binding.edtStudNum.getText().toString();
        String email = binding.edtEmail.getText().toString();
//        String birth = binding.edtBirthdate.getText().toString();
        String gender = "";
        String state = binding.spnState.getSelectedItem().toString();

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = inputFormat.parse(binding.edtBirthdate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String birth = outputFormat.format(date);

        if(binding.rbMale.isChecked())
            gender = binding.rbMale.getText().toString();
        else if(binding.rbFemale.isChecked())
            gender = binding.rbFemale.getText().toString();

        student = new Student(fullname,studNo,email,gender,birth,state);

        fnAddToSqli();

        students.add(student);
        adapter.notifyItemInserted(students.size());
    }

    private void fnAddToSqli() {
        StudentsDB studentsDB = new StudentsDB(this);
        studentsDB.fnInsertStudent(student);
    }

    private void fnAddToRest(View view) {
//        String strURL = "http://10.131.75.141/RESTAPI/rest_api.php";

        fnAdd();

        new Thread(() -> {
            String strURL = "http://192.168.0.115/RESTAPI/rest_api.php";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, response -> {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "Respond from server: " + jsonObject.getString("respond"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }, error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show())
            {
                @Override
                protected Map<String, String> getParams() {

                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = inputFormat.parse(binding.edtBirthdate.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String convertedBirthDate = outputFormat.format(date);

                    String gender = "";

                    String fullname = binding.edtFullName.getText().toString();
                    String studNo = binding.edtStudNum.getText().toString();
                    String email = binding.edtEmail.getText().toString();
                    String state = binding.spnState.getSelectedItem().toString();

                    if(binding.rbMale.isChecked())
                        gender = binding.rbMale.getText().toString();
                    else if(binding.rbFemale.isChecked())
                        gender = binding.rbFemale.getText().toString();

                    Map<String, String> params = new HashMap<>();
                    params.put("selectFn", "fnSaveData");
                    params.put("studName", fullname);
                    params.put("studNo", studNo);
                    params.put("studDob", convertedBirthDate);
                    params.put("studGender", gender);
                    params.put("studState", state);
                    params.put("studEmail", email);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }).start();
    }

    public void fnGetAllFromRest() {
        String strURL = "http://192.168.0.115/RESTAPI/rest_api.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, response -> {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);

//                Toast.makeText(getApplicationContext(), "Respond from server: " + jsonObject.getJSONArray("respond").toString() , Toast.LENGTH_SHORT).show();

                JSONArray jsonArray = jsonObject.getJSONArray("respond");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Student student = new Student();
                    JSONObject object = jsonArray.getJSONObject(i);

                    String fullname = object.getString("studName");
                    String studNo = object.getString("studNo");
                    String email = object.getString("studEmail");
                    String gender = object.getString("studGender");
                    String birth = object.getString("studDob");
                    String state = object.getString("studState");

                    student = new Student(fullname,studNo,email,gender,birth,state);

                    students.add(student);
                    adapter.notifyDataSetChanged();
                }
                Toast.makeText(getApplicationContext(), "Respond from server: " + "get from remote server", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Failed to retrieve from server " + e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, error -> Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show())
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSelectAll");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}