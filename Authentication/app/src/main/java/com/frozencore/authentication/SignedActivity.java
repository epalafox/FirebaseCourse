package com.frozencore.authentication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Signed Activity shows the Authorized users email
 */
public class SignedActivity extends Activity {
    FirebaseAuth mAuth;
    TextView logedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);
        //We initialize the variables
        mAuth = FirebaseAuth.getInstance();
        logedUser = findViewById(R.id.tvEmail);

        //We get the current Firebase user
        FirebaseUser user = mAuth.getCurrentUser();
        logedUser.setText(user.getEmail());

        //We have access to this other variables
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        boolean emailVerified = user.isEmailVerified();
        String uid = user.getUid();
    }
}
