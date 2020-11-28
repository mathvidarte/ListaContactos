package com.example.listacontactos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user;
    private EditText password;
    private Button addBtn;
    private Button signUpLink;
    private String myUser;

    private FirebaseDatabase db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        signUpLink = findViewById(R.id.signUpLink);
        addBtn = findViewById(R.id.addBtn);


        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        addBtn.setOnClickListener(this);
        signUpLink.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                auth.signInWithEmailAndPassword(user.getText().toString(), password.getText().toString()).addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(this, Contact_Activity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                break;
            case R.id.signUpLink:
                Intent i = new Intent(this, SingInActivity.class);
                startActivity(i);
                break;

        }
    }
}