package com.example.listacontactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Contact_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText number;
    private TextView theUser;
    private Button newContact, logOutBtn;
    private ListView contactList;

    private String myUser;

    private Usuario user;

    private FirebaseDatabase db;
    private FirebaseAuth auth;

   private ContactoAdaptador adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null) {
            goToLogin();
        } else {

            name = findViewById(R.id.name);
            number = findViewById(R.id.number);
            newContact = findViewById(R.id.newContact);
            contactList = findViewById(R.id.contactList);
            theUser = findViewById(R.id.theUser);
            logOutBtn = findViewById(R.id.logOutBtn);

            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.CALL_PHONE,
            },  1);

            adapter = new ContactoAdaptador();
            contactList.setAdapter(adapter);

            newContact.setOnClickListener(this);
            logOutBtn.setOnClickListener(this);





            loadDatabase();
            recoverUser();
        }


    }

    private void goToLogin() {
        Intent i =new Intent(this, LogInActivity.class);
        startActivity(i);
        finish();
    }

    private void recoverUser() {
        if (auth.getCurrentUser() != null) {
            String id = auth.getCurrentUser().getUid();
            myUser = id;
            db.getReference().child("users").child(id).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            user = snapshot.getValue(Usuario.class);
                            theUser.setText("Hola"+" "+user.getNombre());
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    }
            );
        }
    }

    private void loadDatabase() {
        DatabaseReference ref = db.getReference().child("users");
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
                String id = db.getReference().child("users").child(myUser).child("contacts").push().getKey();
                String names = name.getText().toString();
                String numbers = number.getText().toString();
              //DatabaseReference reference = db.getReference().child("usuario").child(myUsername);
                DatabaseReference reference = db.getReference().child("users").child(myUser).child("contacts");

                Contacto contactos = new Contacto (
                        name.getText().toString(),
                        number.getText().toString(),
                        id
                );
                reference.setValue(contactos);

                break;
            case R.id.logOutBtn:
                auth.signOut();
                goToLogin();
               /* AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("Cerrar Sessión")
                        .setMessage("¿Estás seguro que quieres cerrar sesión?")
                        .setNegativeButton("No", (dialog, id) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton("Si", (dialog, id) -> {
                            auth.signOut();
                            goToLogin();
                        });
                builder.show();*/
                break;
        }
    }
}

