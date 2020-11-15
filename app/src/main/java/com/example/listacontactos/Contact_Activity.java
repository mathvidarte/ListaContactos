package com.example.listacontactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Contact_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText number;
    private Button newContact;
    private ListView contactList;
    private FirebaseDatabase db;
    private String myUsername;

   private ContactoAdaptador adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        newContact = findViewById(R.id.newContact);
        contactList = findViewById(R.id.contactList);

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.CALL_PHONE,
        },  1);

        myUsername = getSharedPreferences("usuarios", MODE_PRIVATE).getString("username", "NO_USERNAME");

        adapter = new ContactoAdaptador();
        contactList.setAdapter(adapter);

        newContact.setOnClickListener(this);

        db = FirebaseDatabase.getInstance();

        loadDatabase();
    }

    private void loadDatabase() {
        DatabaseReference ref = db.getReference().child("usuario");
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        adapter.clear();
                        for (DataSnapshot child : data.getChildren()) {
                            Contacto contacto = child.getValue(Contacto.class);
                            adapter.addContact(contacto);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newContact:


              //  String id = db.getReference().child("usuario").child(myUsername).push().getKey();
                String id = db.getReference().child("usuario").push().getKey();
                String names = name.getText().toString();
                String numbers = number.getText().toString();
              //DatabaseReference reference = db.getReference().child("usuario").child(myUsername);
                DatabaseReference reference = db.getReference().child("usuario").child(id);

                Contacto contactos = new Contacto (
                        name.getText().toString(),
                        number.getText().toString(),
                        id
                );
                reference.setValue(contactos);

                break;
        }
    }
}