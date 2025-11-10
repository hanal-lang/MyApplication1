package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin, btnRegister, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        initalize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initalize();
    }

    private void initalize() {
        var sp = getSharedPreferences("user_details", MODE_PRIVATE);
        var spName = sp.getString("username", "");
        if(spName==null || spName.isBlank())
            btnLogout.setVisibility(View.GONE);
        else
            btnLogout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin)
            login();
        else if (v == btnRegister)
            register();
        else if (v == btnLogout)
            logout();
    }

    void logout(){
        var sp = getSharedPreferences("user_details", MODE_PRIVATE);
        var spEditor = sp.edit();
        spEditor.clear();
        spEditor.commit();
        btnLogout.setVisibility(View.GONE);
    }
    void login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}