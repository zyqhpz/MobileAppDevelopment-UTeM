package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    Bitmap bp;
    TextView tv;

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        tv = findViewById(R.id.textView2);
////        tv.setText("Failed to fetch picture");
//
//        imageView = findViewById(R.id.imageView);
//
////        Bundle bundle = getIntent().getExtras();
////        if (bundle != null) {
////            int resId = bundle.getInt("pic");
////            imageView.setImageResource(resId);
////        }
//
//        try {
////            Bundle bundle = getIntent().getExtras();
////            Bitmap bmp = (Bitmap) bundle.getParcelable("pic");
//
//            byte[] byteArray = getIntent().getByteArrayExtra("pic");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//            tv.setText("Here is your image!");
////            int resId = bundle.getInt("pic");
////            imageView.setImageResource(resId);
//
////            Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
//
//            imageView.setImageBitmap(bmp);
//
//        } catch (Exception e) {
//            tv.setText("Failed to fetch picture");
//        }
//    }

    @Override
    protected void onRestart() {

        super.onRestart();

        Intent intent = new Intent();
//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("pic");

        tv.setText("Here is your image! " + getIntent().getStringExtra("text"));

        if(getIntent().hasExtra("byteArray")) {
//            ImageView previewThumbnail = new ImageView(this);
//            Bitmap b = BitmapFactory.decodeByteArray(
//                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
//            previewThumbnail.setImageBitmap(b);

            bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            imageView.setImageBitmap(bp);

//            tv.setText("Here is your image! " + getIntent().getStringExtra("text"));
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
//        startActivityForResult(new Intent(this, ThreadedActivity.class), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
//                String strEditText = data.getStringExtra("editTextValue");
//                byte[] byteArray = getIntent().getByteArrayExtra("byteArray");
//                bp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                imageView.setImageBitmap(bp);

//                data.getStringExtra("text");

//                tv.setText("Here is your image! " + getIntent().getStringExtra("text"));
                tv.setText("Here is your image! " + data.getStringExtra("text"));
            }
            else {
                tv.setText("Failed from activity 2");
            }
        }
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        TextView tv = findViewById(R.id.textView2);
//            tv.setText("Failed to fetch picture");
//
//        Bitmap bp = (Bitmap) data.getExtras().get("pic");
//        imageView.setImageBitmap(bp);
//    }
}