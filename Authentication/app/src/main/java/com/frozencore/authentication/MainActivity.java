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

public class MainActivity extends Activity {
    FirebaseAuth mAuth;
    EditText etUser;
    EditText etPassword;
    Button login;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null)
        {
            Intent intent = new Intent(MainActivity.this, SignedActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
        etUser = findViewById(R.id.etusuario);
        etPassword = findViewById(R.id.etpassword);
        login = findViewById(R.id.login);
        registrar = findViewById(R.id.registrarse);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(etUser.getText().toString(), etPassword.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent intent = new Intent(MainActivity.this, SignedActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Log.w(TAG, "signInWithEmail: failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Auth failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
