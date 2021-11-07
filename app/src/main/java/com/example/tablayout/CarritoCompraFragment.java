package com.example.tablayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class CarritoCompraFragment extends ListFragment {

    //Button comprar; = (Button)findViewById(R.id.buttonFinalizarCompra);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SQLiteOpenHelper gameDbHelper = new BBDD(getContext()) ;
        SQLiteDatabase db = gameDbHelper.getReadableDatabase();
        Cursor cursor = db.query("pedido",
                new String[] {"_id", "NAME"},
                null,
                null,
                null, null, null);
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"NAME"},
                new int[] {android.R.id.text1},
                0);
        setListAdapter(listAdapter);

        //comprar = (Button)findViewById(R.id.buttonFinalizarCompra);

        View view = inflater.inflate(R.layout.fragment_carrito_compra,
                container, false);

        Button button = (Button) view.findViewById(R.id.buttonEnviarCarritoInicial);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                Log.d("Finalizado","0");
                Intent i = new Intent(v.getContext(),FinalizarCompra2.class);
                startActivity(i);
            }
        });
        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_carrito_compra, container, false);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Log.d("Eliminamos item","0");
        //Intent intent = new Intent(getActivity(), VistaDetalleJuegosIniciales.class);
        SQLiteOpenHelper gameDbHelper = new BBDD(getContext()) ;
        try
        {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.query("pedido",
                    new String[] {"_id"},
                    null,
                    null,
                    null, null, null);
            cursor.move(position+1);
            BBDD.eliminarPedido(db,cursor.getInt(0));
            //intent.putExtra("GAMEID", cursor.getString(0));
            //startActivity(intent);
        }
        catch (Exception e) {
        }
    }





}