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
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Main Activity shows a Login form that connects with Firebase Authorization
 */
public class MainActivity extends Activity {
    FirebaseAuth fbAuth;
    EditText etUser;
    EditText etPassword;
    Button btnLogin;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Checks if Firebase User is already authenticated, if so: move to the Signed Activity and finish this Activity
        fbAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fbAuth.getCurrentUser();
        if(user != null)
        {
            Intent intent = new Intent(MainActivity.this, SignedActivity.class);
            startActivity(intent);
            finish();
        }
        //if not authenticated we initialize our controls
        setContentView(R.layout.activity_main);
        etUser = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We check if the user typed the values
                String user;
                String password;
                if(etUser.getText().toString().equals("") || etPassword.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, R.string.enterUser, Toast.LENGTH_LONG).show();
                }
                else {
                    user = etUser.getText().toString();
                    password = etPassword.getText().toString();
                    fbAuth.signInWithEmailAndPassword(user,password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(MainActivity.this, SignedActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        //We log the exception and notify the user
                                        Log.w(TAG, "signInWithEmail: failure", task.getException());
                                        Toast.makeText(MainActivity.this, R.string.authFailed, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We move to the Register Activity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                //We need to finish the Activity because the register will automatically sign us in
                finish();
            }
        });

    }
}
