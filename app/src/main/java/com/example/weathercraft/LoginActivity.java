package com.example.weathercraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText userET, passET;
    TextView redirectRegistTV;
    Button loginBTN;

    FirebaseAuth ah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userET = findViewById(R.id.etUsername);
        passET = findViewById(R.id.etPassword);
        loginBTN = findViewById(R.id.btnLogin);
        ah = FirebaseAuth.getInstance();

        redirectRegistTV = findViewById(R.id.tvRegister);

        redirectRegistTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = userET.getText().toString();
                String passInput = passET.getText().toString();
                if(TextUtils.isEmpty(userInput)){
                    Toast.makeText(LoginActivity.this, "Please enter username",
                            Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(passInput)){
                    Toast.makeText(LoginActivity.this, "Please enter password",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                ah.signInWithEmailAndPassword(userInput, passInput)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }
}