package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VistaDetalleJuegosPS4 extends AppCompatActivity {
    int gameId;
    private Button agregarALaCestaPS4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detalle_juegos_ps4);


        gameId = Integer.valueOf(getIntent().getStringExtra("GAMEID"));
        SQLiteOpenHelper gameDbHelper = new BBDD(this) ;
        try {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.query("PS4",
                    new String[]{"_id", "NAME", "COMPANY", "IMAGE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(gameId)},
                    null, null, null);
            cursor.moveToFirst();
            gameId = cursor.getInt(0);
            ((TextView)findViewById(R.id.NombreJuego)).setText(cursor.getString(1));
            ((TextView)findViewById(R.id.DescripcionJuego)).setText(cursor.getString(2));
            //((ImageView)findViewById(R.id.gameImage)).setImageResource(cursor.getInt(3));
            boolean isChecked = (cursor.getInt(4) == 0) ? false : true;
            //((CheckBox)findViewById(R.id.favoriteCheckbox)).setChecked(isChecked);

            agregarALaCestaPS4 = findViewById(R.id.ButonHacerPedidoPS4);

            agregarALaCestaPS4.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Log.d("Hecho compra", "0");
                    BBDD.addPedido(db, cursor.getString(1), cursor.getString(2));
                }
            });
        }
        catch (Exception e){
        }


    }
}