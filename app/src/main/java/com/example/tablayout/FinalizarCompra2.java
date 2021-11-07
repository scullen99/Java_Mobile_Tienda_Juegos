package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FinalizarCompra2 extends AppCompatActivity {
    private Button finalizarPedido;
    private EditText Nombre;
    private EditText DireccionJuego;
    private EditText EmailJuego;
    private EditText TelefonoJuego;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra2);

        Nombre = findViewById(R.id.NombreJuego);
        DireccionJuego = findViewById(R.id.DireccionJuego);
        EmailJuego = findViewById(R.id.EmailJuego);
        TelefonoJuego = findViewById(R.id.TelefonoJuego);

        finalizarPedido = findViewById(R.id.ButtonFinalizarPedidoActivity);

        SQLiteOpenHelper gameDbHelper = new BBDD(this) ;
        SQLiteDatabase db = gameDbHelper.getReadableDatabase();
        cursor = db.query("pedido",
                 new String[] {"_id", "NAME"},
                null,
                null,
                null, null, null);

        finalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Funciona finalizar","0");

                String[] TO = {"juanidiez@hotmail.es"}; //Direcciones email  a enviar.
                String[] CC = {""}; //Direcciones email con copia.



                String emailText = Nombre.getText().toString() + "\n"
                        + DireccionJuego.getText().toString() + "\n"
                        + EmailJuego.getText().toString() + "\n"
                        + TelefonoJuego.getText().toString();


                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {

                    //contactos.add(cursor.getString(cursor.getColumnIndex("DISPLAY_NAME"))); //add the item
                    emailText  += "\n" + cursor.getString(1);
                    cursor.moveToNext();
                }



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
                    Toast.makeText(FinalizarCompra2.this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}