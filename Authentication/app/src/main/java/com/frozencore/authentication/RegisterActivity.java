package com.frozencore.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

/**
 * Register Activity shows a form for the user to sign up
 */

public class RegisterActivity extends Activity {
    FirebaseAuth fbAuth;
    EditText etUser;
    EditText etPassword;
    Button btnReturn;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //We initialize our controls
        fbAuth = FirebaseAuth.getInstance();
        etUser = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnReturn = findViewById(R.id.btnRegresar);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We check if the user typed the values
                String user;
                String password;
                if(etUser.getText().toString().equals("") || etPassword.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this, R.string.enterUser, Toast.LENGTH_LONG).show();
                }
                else {
                    fbAuth.createUserWithEmailAndPassword(etUser.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //We log in the user, show the signed screen and finish this Activity
                                        Intent intent = new Intent(RegisterActivity.this, SignedActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        //We log the exception and notify the user
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this, R.string.authFailed,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We need to add MainActivity to the stack, because it was finished, and remove also this Activity from the stack
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
