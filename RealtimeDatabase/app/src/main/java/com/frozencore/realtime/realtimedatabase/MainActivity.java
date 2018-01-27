package com.frozencore.realtime.realtimedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.frozencore.realtime.realtimedatabase.adapters.TextAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list;
    TextAdapter adapter;
    ListView listView;
    EditText etMessage;
    ImageButton btnSend;
    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_text);
        etMessage = findViewById(R.id.message);
        btnSend = findViewById(R.id.btnsend);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("messages");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMessage.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Favor de ingresar un mensaje", Toast.LENGTH_LONG).show();
                }
                else
                {
                    list.add(etMessage.getText().toString());
                    myRef.setValue(list);
                    etMessage.setText("");
                }
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> typeIndicator = new GenericTypeIndicator<ArrayList<String>>() {};
                list = dataSnapshot.getValue(typeIndicator);
                if(list == null)
                    list = new ArrayList<String>();
                adapter = new TextAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
