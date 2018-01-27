package com.frozencore.authentication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedActivity extends Activity {
    FirebaseAuth mAuth;
    TextView logedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        logedUser = findViewById(R.id.tvEmail);

        logedUser.setText(user.getEmail());

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        boolean emailVerified = user.isEmailVerified();
        String uid = user.getUid();
    }
}
