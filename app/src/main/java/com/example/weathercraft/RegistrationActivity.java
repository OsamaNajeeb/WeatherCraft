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

public class RegistrationActivity extends AppCompatActivity {

    EditText userET, passET;
    TextView redirectLoginTV;
    Button regisBTN;

    FirebaseAuth ah; // Initialize FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        redirectLoginTV = findViewById(R.id.tvLogin);
        userET = findViewById(R.id.etUsername);
        passET = findViewById(R.id.etPassword);
        regisBTN = findViewById(R.id.btnRegsiter);

        // Initialize FirebaseAuth
        ah = FirebaseAuth.getInstance();

        regisBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = userET.getText().toString();
                String passInput = passET.getText().toString();
                if(TextUtils.isEmpty(userInput)){
                    Toast.makeText(RegistrationActivity.this, "Please enter username",
                            Toast.LENGTH_LONG).show();
                    return;
                }if (TextUtils.isEmpty(passInput)){
                    Toast.makeText(RegistrationActivity.this, "Please enter password",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                ah.createUserWithEmailAndPassword(userInput, passInput)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = ah.getCurrentUser();
                                    Toast.makeText(RegistrationActivity.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                    finish();
                                } else {

                                    Toast.makeText(RegistrationActivity.this, " " + task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        redirectLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
