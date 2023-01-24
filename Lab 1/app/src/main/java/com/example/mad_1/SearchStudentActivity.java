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
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
        String strURL = "http://192.168.0.115/RESTAPI/rest_api.php";

        Map<String, String> params = new HashMap<>();
        params.put("selectFn", "fnSearchStud");
        params.put("studNo", binding.edtStudID.getText().toString());

        Toast.makeText(getApplicationContext(), binding.edtStudID.getText().toString(), Toast.LENGTH_SHORT).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, strURL, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = response.getJSONObject(i);
                    binding.txtVwStudName2.setText(jsonObject.getString("studName"));
                    binding.txtVwStudGender.setText(jsonObject.getString("studGender"));
                    binding.txtVwStudNo.setText(jsonObject.getString("studNo"));
                    binding.txtVwStudState.setText(jsonObject.getString("studState"));
                }
            } catch (JSONException e) {
                Log.e("Volley Error", "Failed to parse JSON response", e);
            }
        }, error -> {
            Log.e("Volley Error", "Failed to retrieve data", error);
        })
        {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        int timeout = 30; // in seconds
        RetryPolicy policy = new DefaultRetryPolicy(timeout * 1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonArrayRequest.setRetryPolicy(policy);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}