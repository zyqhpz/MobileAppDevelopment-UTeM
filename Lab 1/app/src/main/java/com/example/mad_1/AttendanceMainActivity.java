package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.example.mad_1.databinding.ActivityStudentMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class AttendanceMainActivity extends AppCompatActivity {

    private ActivityStudentMainBinding binding;
    private Student student;

    private Vector<Student> students;
    private RecyclerView.Adapter adapter;

    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        binding = ActivityStudentMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.fabAdd.setOnClickListener(this::fnAddToRest);

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
                datePicker = new DatePickerDialog(AttendanceMainActivity.this,
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

    private void fnAddToRest(View view) {
        String strURL = "http://10.131.75.141/RESTAPI/rest_api.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), "Respond from server: " + jsonObject.getString("respond"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = inputFormat.parse(binding.edtBirthdate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String convertedDate = outputFormat.format(date);

                String gender = "";

                if(binding.rbMale.isChecked())
                    gender = binding.rbMale.getText().toString();
                else if(binding.rbFemale.isChecked())
                    gender = binding.rbFemale.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSaveData");
                params.put("studName", binding.edtFullName.getText().toString());
                params.put("studNo", binding.edtStudNum.getText().toString());
                params.put("studDob", convertedDate);
                params.put("studGender", gender);
                params.put("studState", binding.spnState.getSelectedItem().toString());
                params.put("studEmail", binding.edtEmail.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}