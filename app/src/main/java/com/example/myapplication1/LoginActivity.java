package com.example.myapplication1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogin;
    EditText etUsername, etPassword;
    SharedPreferences sp;
    String spName = "";
    String spPsw = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        btnLogin = findViewById(R.id.btnLogin);
        // Assuming the IDs in your login_layout.xml are etUsername and etPassword
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin.setOnClickListener(this);

        initalize();
    }

    private void initalize() {
        sp = getSharedPreferences("user_details", MODE_PRIVATE);
        spName = sp.getString("username", "");
        spPsw = sp.getString("password", "");

        etUsername.setText(spName);
        etPassword.setText(spPsw);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin)
            login();
    }

    private void login() {
        if(spName == null || spPsw.isBlank()) {
            Toast.makeText(this, "No user found, Need to register first", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
        else {
            if(!etUsername.getText().toString().equals(spName) || !etPassword.getText().toString().equals(spPsw)) {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                return;
            }
            else
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}