package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactoActivity extends AppCompatActivity {

    private EditText ContactoNombrePersona;
    private EditText ContactoEmailPersona;
    private EditText ContactoConsultaPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Button contacto = findViewById(R.id.EnviarContacto);
        ContactoNombrePersona = findViewById(R.id.ContactoNombrePersona);
        ContactoEmailPersona = findViewById(R.id.ContactoEmailPersona);
        ContactoConsultaPersona = findViewById(R.id.ContactoConsultaPersona);

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] TO = {"juanidiez@hotmail.es"}; //Direcciones email  a enviar.
                String[] CC = {""}; //Direcciones email con copia.

                String emailText = ContactoNombrePersona.getText().toString() + "\n"
                        + ContactoEmailPersona.getText().toString() + "\n"
                        + ContactoConsultaPersona.getText().toString() + "\n";

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Pedido");
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Enviar email."));
                    Log.i("EMAIL", "Enviando email...");
                }
                catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(ContactoActivity.this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}