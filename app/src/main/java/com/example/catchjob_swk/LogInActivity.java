package com.example.catchjob_swk;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogInActivity extends SignUpActivity {


    private String TAG ="LoginActivity";

    public FirebaseAuth mAuth;
    private Button SignUp;
    private Button LogIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignUp = findViewById(R.id.btn_signup);
        LogIn = findViewById(R.id.btn_login);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn();
            }
        });
    }

    private void LogIn() {

        String Email = ((EditText)findViewById(R.id.login_id)).getText().toString();
        String Password = ((EditText)findViewById(R.id.login_pw)).getText().toString();

        mAuth = FirebaseAuth.getInstance();


        if(Email.length() > 0 && Password.length() > 0) {

            mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "Login:success");
                                Intent intent = new Intent(LogInActivity.this, NextActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "Login:failure", task.getException());
                                ViewToast("아이디 혹은 비밀번호가 유효하지 않습니다.");
                            }
                        }
                    });
        }
        else {
            ViewToast("아이디 혹은 비밀번호를 입력하시오.");
        }

    }
}