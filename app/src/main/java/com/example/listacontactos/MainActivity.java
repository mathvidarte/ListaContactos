package com.example.listacontactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private Button addBtn;
    private FirebaseDatabase db;
    private String myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.CALL_PHONE,
        }, requestCode: 1);

        user = findViewById(R.id.user);
        addBtn = findViewById(R.id.addBtn);
        db = FirebaseDatabase.getInstance();

        addBtn.setOnClickListener(
                (v)-> {
                    Intent i = new Intent(this, Contact_Activity.class);
                    String username = user.getText().toString();
                  //  i.putExtra("username", username);
                    SharedPreferences preference = getSharedPreferences("usuarios", MODE_PRIVATE);
                    preference.edit().putString("username", username).apply();
                    startActivity(i);
                }
        );
    }


}