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

public class ThreadedActivity extends AppCompatActivity {

    ImageView imgVwPic;
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

        Intent intent = new Intent();
//            intent.putExtra("pic", (CharSequence) imgVwPic);
//                    intent.putExtra("pic", R.id.imgVwProfile);


        imgVwPic.buildDrawingCache();
        Bitmap image= imgVwPic.getDrawingCache();

        Bundle extras = new Bundle();
        extras.putParcelable("pic", image);
        intent.putExtras(extras);
//            startActivity(intent);

        setResult(RESULT_OK, intent);

        finish();

//        startActivity(intent);
    }

    public void fnTakePic(View view)
    {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvGreet.setText("Opening camera");
                    }
                });

            }
        };

        Thread thr = new Thread(run);
        thr.start();
    }

//    public void onBackPressed() {
//
//    }

    public void onBackPressed() {
//        Bundle bundle = new Bundle();
//        bundle.putString(FIELD_A, mA.getText().toString());


        try {
            Intent intent = new Intent();
//            Intent intent = new Intent(this, ThreadedActivityMain.class);
//            intent.putExtra("pic", (CharSequence) imgVwPic);
//                    intent.putExtra("pic", R.id.imgVwProfile);


            imgVwPic = findViewById(R.id.imgVwProfile);
            imgVwPic.buildDrawingCache();
            Bitmap image= imgVwPic.getDrawingCache();

            Bundle extras = new Bundle();
            extras.putParcelable("pic", image);
            intent.putExtras(extras);
//            startActivity(intent);

            setResult(RESULT_OK, intent);
            super.onBackPressed();
        } catch (Exception e) {
            setResult(RESULT_CANCELED);
        }

//        Intent intent = new Intent();
//        intent.putExtra("pic", (CharSequence) imgVwPic);
//        intent.putExtra("pic", R.id.imgVwProfile);
//        intent.putExtras(bundle);
//        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        tvGreet.setText("image snapped");

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imgVwPic.setImageBitmap(bp);
    }
}