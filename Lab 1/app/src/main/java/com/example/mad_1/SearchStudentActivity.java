package com.example.mad_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mad_1.databinding.ActivitySearchStudentBinding;
import com.example.mad_1.databinding.ActivityStudentMainBinding;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.fabAdd.setOnClickListener(this::fnAddToRest);

        binding.btnSearch.setOnClickListener(this::fnSearch);
    }

    private void fnSearch(View view) {
//        String strURL = "http://10.131.75.141/RESTAPI/rest_api.php";
        String strURL = "http://192.168.0.115/RESTAPI/rest_api.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                JSONObject jsonObject = null;
                try {
                    Toast.makeText(getApplicationContext(), "Getting some respond here", Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = new JSONArray(response);

                    Toast.makeText(getApplicationContext(), "get one", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        Toast.makeText(getApplicationContext(), jsonObject.getString("studName"), Toast.LENGTH_SHORT).show();
                        binding.txtVwStudName2.setText(jsonObject.getString("studName"));
                        binding.txtVwStudGender.setText(jsonObject.getString("studGender"));
                        binding.txtVwStudNo.setText(jsonObject.getString("studNo"));
                        binding.txtVwStudState.setText(jsonObject.getString("studState"));
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to fetch data", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String strSearchStudNo = binding.edtStudID.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("selectFn", "fnSearchStud");
                params.put("studNo", strSearchStudNo);

//                Toast.makeText(getApplicationContext(), strSearchStudNo, Toast.LENGTH_SHORT).show();
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}