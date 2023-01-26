package com.example.mad_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mad_1.databinding.ActivityNavigationBinding;
import com.example.mad_1.databinding.ActivityThreadedMainBinding;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class ThreadedActivityMain extends AppCompatActivity {

    Button btnTakePicturePage;
    ImageView imageView;
    Bitmap bp;
    TextView tv;

    ActivityThreadedMainBinding binding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                if (result.getData() != null && result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE) != null) {
                    tv.setText("get from new launcher");
                    tv.setText("get from new launcher " + result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE));

                    if (result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE) != null) {
                        bp = BitmapFactory.decodeByteArray(result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE), 0, result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE).length);
                        imageView = binding.imageView;
                        imageView.setImageBitmap(bp);
                        tv.setText("get from new launcher " + result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE) + " success");
                    } else {
                        tv.setText("get from new launcher " + result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE) + " failed");

                    }

                }
            }
        }
    });

    @Override
    protected void onRestart() {
        super.onRestart();
        tv.setText("Here is your image! " + getIntent().getStringExtra("text"));
        if(getIntent().hasExtra("byteArray")) {
            bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            imageView.setImageBitmap(bp);
        }
        else {
            tv.setText("Failed " + getIntent().getStringExtra("text"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThreadedMainBinding.inflate(getLayoutInflater());
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

        btnTakePicturePage = binding.btnTakePicturePage;
        btnTakePicturePage.setOnClickListener(this::fnNextActivity);

        imageView = binding.imageView;
        tv = binding.textView2;

        if (getIntent().getExtras() != null) {
            onRestart();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fnNextActivity(View view) {
       startForResult.launch(new Intent(this, ThreadedActivity.class));
    }

}