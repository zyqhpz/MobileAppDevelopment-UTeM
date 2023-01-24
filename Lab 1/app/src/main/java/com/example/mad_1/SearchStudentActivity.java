package com.example.mad_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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