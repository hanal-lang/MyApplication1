package com.example.myapplication1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    CheckBox chkSaveDetails;
    EditText etUsername;
    EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister)
            register();
    }

    private void register() {
        Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();

        saveToSharedPreferences();
        finish();
    }

    private void saveToSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("user_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", etUsername.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.commit();
    }
}