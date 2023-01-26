package com.example.mad_1.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mad_1.R;
import com.example.mad_1.databinding.FragmentGetUniversityBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetUniversityFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetUniversityFrag extends Fragment {

    FragmentGetUniversityBinding binding;

    public GetUniversityFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetUniversityFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static GetUniversityFrag newInstance(String param1, String param2) {
        GetUniversityFrag fragment = new GetUniversityFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void fnSearchUni(View view) {
    String strURL = "http://universities.hipolabs.com/search?country=" + binding.edtFindUniversity.getText().toString();
    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
    StringRequest stringRequest = new StringRequest(Request.Method.GET, strURL, new Response. Listener<String>() {
        @Override
        public void onResponse (String response) {
            binding.results.setText(response);
            binding.results.setMovementMethod(new ScrollingMovementMethod());
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse (VolleyError error) {
            Toast.makeText(getContext(), "Unable to connect to the university list!" + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    requestQueue.add (stringRequest);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGetUniversityBinding.inflate(inflater, container, false);
        binding.btnSearchU.setOnClickListener(this::fnSearchUni);
        return binding.getRoot();
    }
}