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

public class ActivityReclamacion extends AppCompatActivity {

    private EditText ReclamacionNombrePersona;
    private EditText ReclamacionEmailPersona;
    private EditText ReclamacionMotivoPersona;
    private EditText ReclamacionFacturaPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacion);

        Button contacto = findViewById(R.id.EnviarReclamacion);
        ReclamacionNombrePersona = findViewById(R.id.ReclamacionNombrePersona);
        ReclamacionEmailPersona = findViewById(R.id.ReclamacionEmailPersona);
        ReclamacionMotivoPersona = findViewById(R.id.ReclamacionMotivoPersona);
        ReclamacionFacturaPersona = findViewById(R.id.ReclamacionFacturaPersona);


        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] TO = {"juanidiez@hotmail.es"}; //Direcciones email  a enviar.
                String[] CC = {""}; //Direcciones email con copia.

                String emailText = ReclamacionNombrePersona.getText().toString() + "\n"
                        + ReclamacionEmailPersona.getText().toString() + "\n"
                        + ReclamacionMotivoPersona.getText().toString() + "\n"
                        + ReclamacionFacturaPersona.getText().toString();

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
                    Toast.makeText(ActivityReclamacion.this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}