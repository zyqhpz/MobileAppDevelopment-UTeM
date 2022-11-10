package com.example.mad_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class ThreadedActivity extends AppCompatActivity {

    ImageView imgVwPic;
    Bitmap bp;
    ByteArrayOutputStream bStream;
    byte[] byteArray;

    TextView tvGreet;
    Button btnTakePic;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        imgVwPic = findViewById(R.id.imgVwProfile);
        Intent intent = getIntent();

        String strMsg = intent.getStringExtra("varStr1");
        tvGreet = findViewById(R.id.tvGreet);
        tvGreet.setText("Welcome to second activity " + strMsg);

        btnTakePic = findViewById(R.id.btnTakePic);
        btnTakePic.setOnClickListener(this::fnTakePic);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this::fnBackToPrevious);
    }

    private void fnBackToPrevious(View view) {
//        Intent intent = new Intent(this, ThreadedActivityMain.class);
//        intent.putExtra("pic", (CharSequence) imgVwPic);

//        Intent intent = new Intent();
////            intent.putExtra("pic", (CharSequence) imgVwPic);
////                    intent.putExtra("pic", R.id.imgVwProfile);
//
//
//        imgVwPic.buildDrawingCache();
//        Bitmap image= imgVwPic.getDrawingCache();
//
//        Bundle extras = new Bundle();
//        extras.putParcelable("pic", image);
//        intent.putExtras(extras);
////            startActivity(intent);
//
//        setResult(RESULT_OK, intent);
//
//        finish();

        Intent intent = new Intent(this, ThreadedActivityMain.class);
        byteArray = bStream.toByteArray();
//        intent.putExtra("pic", byteArray);
        intent.putExtra("text", "from camera");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void fnTakePic(View view)
    {
        Runnable run = () -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvGreet.setText("Opening camera");
                }
            });

        };

        Thread thr = new Thread(run);
        thr.start();
    }

//    public void onBackPressed() {
//
//    }

    @Override
    public void onBackPressed() {
//        Bundle bundle = new Bundle();
//        bundle.putString(FIELD_A, mA.getText().toString());

//
        try {
            Intent intent = new Intent();
////            Intent intent = new Intent(this, ThreadedActivityMain.class);
////            intent.putExtra("pic", (CharSequence) imgVwPic);
////                    intent.putExtra("pic", R.id.imgVwProfile);
//
//
//            imgVwPic = findViewById(R.id.imgVwProfile);
//            imgVwPic.buildDrawingCache();
//            Bitmap bp = imgVwPic.getDrawingCache();
//
//            Bundle extras = new Bundle();
//            extras.putParcelable("pic", image);
//            intent.putExtras(extras);
////            startActivity(intent);
            byteArray = bStream.toByteArray();

//            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            //bp.compress(Bitmap.CompressFormat.PNG, 100, bStream);
//            byteArray = bStream.toByteArray();

//            Intent anotherIntent = new Intent(this, anotherActivity.class);
//            intent.putExtra("pic", byteArray);

//            intent.putExtra("byteArray", byteArray);
            intent.putExtra("text", "From camera activity");
//            startActivity(anotherIntent);
//            finish();

            setResult(RESULT_OK, intent);
            finish();
//            super.onBackPressed();
        } catch (Exception e) {
            setResult(RESULT_CANCELED);
            finish();
        }
//
////        Intent intent = new Intent();
////        intent.putExtra("pic", (CharSequence) imgVwPic);
////        intent.putExtra("pic", R.id.imgVwProfile);
////        intent.putExtras(bundle);
////        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        tvGreet.setText("image snapped");

        bp = (Bitmap) data.getExtras().get("data");

        bStream = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.PNG, 100, bStream);

//        byteArray = bStream.toByteArray();

        imgVwPic.setImageBitmap(bp);
    }
}