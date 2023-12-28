package com.example.weathercraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAdminActivity extends AppCompatActivity {
    EditText userET, passET;
    Button loginBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        userET = findViewById(R.id.etUsername);
        passET = findViewById(R.id.etPassword);
        loginBTN = findViewById(R.id.btnLogin);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = userET.getText().toString();
                String passInput = passET.getText().toString();

                if (TextUtils.isEmpty(userInput) || !userInput.equals("Osama@gmail.com")) {
                    Toast.makeText(LoginAdminActivity.this, "Please enter a valid email",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(passInput) || !passInput.equals("12345@")) {
                    Toast.makeText(LoginAdminActivity.this, "Please enter a valid password",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                startActivity(new Intent(LoginAdminActivity.this, AdminActivity.class));
                finish();
            }
        });
    }
}
