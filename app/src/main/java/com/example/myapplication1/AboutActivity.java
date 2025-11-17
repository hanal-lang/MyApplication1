package com.example.myapplication1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    Button btnCallUs, btnEmailUs, btnSendSms, btnTakePicture, btnClose;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCallUs = findViewById(R.id.btnCallUs);
        btnEmailUs = findViewById(R.id.btnEmailUs);
        btnSendSms = findViewById(R.id.btnTextUs);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        btnClose = findViewById(R.id.btnClose);
        imageView = findViewById(R.id.imgImage1);

        btnCallUs.setOnClickListener(this);
        btnEmailUs.setOnClickListener(this);
        btnSendSms.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnTakePicture.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v==btnCallUs){
            callUs();
        }
        else if(v==btnEmailUs){
            emailUs();
        }
        else if(v==btnSendSms){
            sendSms();
        }
        else if(v==btnTakePicture){
            takePicture();
        }
        else if(v==btnClose){
            finish();
        }
    }

    private void callUs() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0523455408"));
        startActivity(intent);
    }

    private void emailUs() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ymushkin1@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "message");
        startActivity(intent);
    }

    private void sendSms() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:0523455408"));
        intent.putExtra("sms_body", "message");
        startActivity(intent);
    }

    private void takePicture() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error message
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            imageView.setVisibility(View.VISIBLE);
        }
    }
}