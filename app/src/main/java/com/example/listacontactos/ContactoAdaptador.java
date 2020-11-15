package com.example.listacontactos;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ContactoAdaptador extends BaseAdapter implements View.OnClickListener {

    private Contacto contacto;
    private ArrayList<Contacto> contactos;

    public ContactoAdaptador() {
        contactos = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addContact (Contacto contacto) {
        contactos.add(contacto);
        notifyDataSetChanged();
    }

    public void clear () {
        contactos.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int pos, View renglon, ViewGroup lista) {

        LayoutInflater inflater = LayoutInflater.from(lista.getContext());
        View renglonView = inflater.inflate(R.layout.row, null);

        contacto = contactos.get(pos);

        Button callBtn = renglonView.findViewById(R.id.callBtn);
        Button deleteBtn = renglonView.findViewById(R.id.deleteBtn);
        TextView nombreRow = renglonView.findViewById(R.id.nombreRow);
        TextView numberRow = renglonView.findViewById(R.id.numberRow);

        nombreRow.setText(contacto.getNombre());
        numberRow.setText(contacto.getTelefono());

        deleteBtn.setOnClickListener(this);
        callBtn.setOnClickListener(this);


        return renglonView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deleteBtn:
                String id = contacto.getId();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("usuario").child(id);
                ref.setValue(null);
                break;
            case R.id.callBtn:
                String tel = "tel:"+contacto.getTelefono();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(tel));
                

        }
    }
}
