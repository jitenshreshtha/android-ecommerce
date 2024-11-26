package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, password, confirmPassword;
    private Button signupBtn;
    private TextView loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signupUsername);
        password = findViewById(R.id.signupPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        signupBtn = findViewById(R.id.signupBtn);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newintent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(newintent);
            }
        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Signup successful
                                    Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                                    // Navigate to another activity or update UI
                                    Intent newIntent2 = new Intent(SignupActivity.this, HomeActivity.class);
                                    startActivity(newIntent2);
                                } else {
                                    // Signup failed
                                    Toast.makeText(SignupActivity.this, "Signup failed: " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    Log.e("SignupActivity", "Error: ", task.getException());
                                }
                            }
                        });
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}