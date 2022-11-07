package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ThreadedActivityMain extends AppCompatActivity {

    Button btnTakePicturePage;
    ImageView imageView;
    TextView tv;

    @Override
    protected void onRestart() {
        super.onRestart();
        tv = findViewById(R.id.textView2);
//        tv.setText("Failed to fetch picture");

        imageView = findViewById(R.id.imageView);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            int resId = bundle.getInt("pic");
//            imageView.setImageResource(resId);
//        }

        try {
            Bundle bundle = getIntent().getExtras();
            Bitmap bmp = (Bitmap) bundle.getParcelable("pic");

            tv.setText("Here is your image!");
//            int resId = bundle.getInt("pic");
//            imageView.setImageResource(resId);

//            Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

            imageView.setImageBitmap(bmp );

        } catch (Exception e) {
            tv.setText("Failed to fetch picture");
        }
    }
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        tv = findViewById(R.id.textView2);
//        tv.setText("Failed to fetch picture");
//
//        imageView = findViewById(R.id.imageView);
//
////        Bundle bundle = getIntent().getExtras();
////        if (bundle != null) {
////            int res_image = bundle.getInt("pic");
////            imageView = findViewById(R.id.imageView);
////            imageView.setImageResource(res_image);
//        }

//        try {
////            Intent intent = getIntent();
////            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("pic");
////            imageView.setImageBitmap(bitmap);
//            tv.setText("Here is your picture!!");
//        } catch (Exception e) {
////            TextView tv = findViewById(R.id.textView2);
//            tv.setText("Failed to fetch picture");
//        }
//    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        TextView tv = findViewById(R.id.textView2);
//        tv.setText("Failed to fetch picture");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded_main);

        btnTakePicturePage = findViewById(R.id.btnTakePicturePage);
        btnTakePicturePage.setOnClickListener(this::fnNextActivity);

        imageView = findViewById(R.id.imageView);

//        Intent intent = new Intent();

//        try {
//            Intent intent = getIntent();
//            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("pic");
//            imageView.setImageBitmap(bitmap);
//        } catch (Exception e) {
//            TextView tv = findViewById(R.id.textView2);
//            tv.setText("Failed to fetch picture");
//        }
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            int res_image = bundle.getInt("pic");
//            imageView.setImageResource(res_image);
//        }

//        try {
//            tv.setText("Here is your picture!!");
//        } catch (Exception e) {
//            tv.setText("Failed to fetch picture");
//        }





//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("pic");


    }

//    public void onBackPressed() {
//        try {
//            Intent intent = getIntent();
//            Bitmap bitmap = intent.getParcelableExtra("pic");
//            imageView.setImageBitmap(bitmap);
//        } catch (Exception e) {
//            TextView tv = findViewById(R.id.textView2);
//            tv.setText("Failed to fetch picture");
//        }
//    }

    private void fnNextActivity(View view) {
//        Intent intent = Intent(this, SecondActivity.class);
        startActivity(new Intent(this, ThreadedActivity.class));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView tv = findViewById(R.id.textView2);
            tv.setText("Failed to fetch picture");

        Bitmap bp = (Bitmap) data.getExtras().get("pic");
        imageView.setImageBitmap(bp);
    }
}