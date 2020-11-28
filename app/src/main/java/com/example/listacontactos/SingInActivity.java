package com.example.listacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SingInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, correo, password, rpassword;
    private Button signUpBtn;
    private Button logInLink;

    private FirebaseDatabase db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        name = findViewById(R.id.name);
        correo = findViewById(R.id.correo);
        password = findViewById(R.id.password);
        rpassword = findViewById(R.id.rpassword);
        signUpBtn = findViewById(R.id.signUpBtn);
        logInLink = findViewById(R.id.logInLink);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(this);
        logInLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.signUpBtn:
                auth.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString()).addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                String id = auth.getCurrentUser().getUid();
                                Usuario user = new Usuario(
                                        name.getText().toString(),
                                        correo.getText().toString(),
                                        password.getText().toString(),
                                        rpassword.getText().toString(),
                                        id
                                );
                                db.getReference().child("users").child(id).setValue(user).addOnCompleteListener(
                                        taskdb -> {
                                            if (taskdb.isSuccessful()){
                                                Intent i = new Intent(this, Contact_Activity.class);
                                                startActivity(i);
                                                finish();
                                            } else {
                                                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                );
                            } else {
                                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                break;
            case R.id.logInLink:
                finish();
                break;
        }
    }
}