package com.example.mad_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mad_1.databinding.ActivitySearchStudentBinding;
import com.example.mad_1.databinding.ActivityStudentMainBinding;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SearchStudentActivity extends AppCompatActivity {

    ActivitySearchStudentBinding binding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchStudentBinding.inflate(getLayoutInflater());
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

        binding.btnSearch.setOnClickListener(this::fnSearch);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnSearch(View view) {
//        String strURL = "http://10.131.75.141/RESTAPI/rest_api.php";
        String strURL = "http://192.168.0.115/RESTAPI/rest_api.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Toast.makeText(getApplicationContext(), binding.edtStudID.getText().toString(), Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, response -> {
            try {
                JSONObject jsonObj = new JSONObject(response);
                JSONArray jsonArray = jsonObj.getJSONArray("respond");

                Toast.makeText(getApplicationContext(), "get one", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    binding.txtVwStudName2.setText(jsonObject.getString("studName"));
                    binding.txtVwStudGender.setText(jsonObject.getString("studGender"));
                    binding.txtVwStudNo.setText(jsonObject.getString("studNo"));
                    binding.txtVwStudState.setText(jsonObject.getString("studState"));
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                Log.e("Volley Error", "Failed to parse JSON response", e);
            }
        }, error -> {
            Toast.makeText(getApplicationContext(), "Unable to fetch data", Toast.LENGTH_SHORT).show();
            Log.e("Volley Error", "Failed to retrieve data", error);
        })
        {
            @Override
            protected Map<String, String> getParams() {

                String strSearchStudNo = binding.edtStudID.getText().toString();
                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSearchStud");
                params.put("studNo", strSearchStudNo);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
}