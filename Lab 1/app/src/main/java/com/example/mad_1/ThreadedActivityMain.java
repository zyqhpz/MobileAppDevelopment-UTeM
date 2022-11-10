package com.example.mad_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                if (result.getData() != null && result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE) != null) {
                    tv.setText("get from new launcher");
                    tv.setText("get from new launcher " + result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE));

                    if (result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE) != null) {
                        bp = BitmapFactory.decodeByteArray(result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE), 0, result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE).length);
                        imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(bp);
                        tv.setText("get from new launcher " + result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE) + " success");
                    } else {
                        tv.setText("get from new launcher " + result.getData().getStringExtra(ThreadedActivity.TEXT_VALUE) + " failed");

                    }

////                    if(getIntent().hasExtra("byteArray")) {
//                        bp = BitmapFactory.decodeByteArray(result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE), 0, result.getData().getByteArrayExtra(ThreadedActivity.IMAGE_SOURCE).length);
//                        imageView.setImageBitmap(bp);

                }
            }
        }
    });

    @Override
    protected void onRestart() {

        super.onRestart();

//        Intent intent = new Intent();
//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("pic");

        tv.setText("Here is your image! " + getIntent().getStringExtra("text"));

        if(getIntent().hasExtra("byteArray")) {

            bp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
            imageView.setImageBitmap(bp);

//            tv.setText("Here is your image! " + getIntent().getStringExtra("text"));
        }
        else {
            tv.setText("Failed " + getIntent().getStringExtra("text"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded_main);

        btnTakePicturePage = findViewById(R.id.btnTakePicturePage);
        btnTakePicturePage.setOnClickListener(this::fnNextActivity);

        imageView = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView2);

        if (getIntent().getExtras() != null) {
            onRestart();
//            tv.setText("Extras exist");
        }

    }


    private void fnNextActivity(View view) {
//        Intent intent = Intent(this, SecondActivity.class);
        // startActivity(new Intent(this, ThreadedActivity.class));
//       startActivityForResult(new Intent(this, ThreadedActivity.class), 2);
       startForResult.launch(new Intent(this, ThreadedActivity.class));
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