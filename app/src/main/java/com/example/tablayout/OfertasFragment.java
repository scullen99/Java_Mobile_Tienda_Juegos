package com.example.tablayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class OfertasFragment extends ListFragment {



    public OfertasFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        SQLiteOpenHelper gameDbHelper = new BBDD(getContext()) ;
        SQLiteDatabase db = gameDbHelper.getReadableDatabase();
        Cursor cursor = db.query("OFERTAS",
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ofertas, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent = new Intent(getActivity(), VistaDetalleJuegosOfertas.class);
        SQLiteOpenHelper gameDbHelper = new BBDD(getContext()) ;
        try
        {
            SQLiteDatabase db = gameDbHelper.getReadableDatabase();
            Cursor cursor = db.query("OFERTAS",
                    new String[] {"_id"},
                    null,
                    null,
                    null, null, null);
            cursor.move(position+1);
            intent.putExtra("GAMEID", cursor.getString(0));
            startActivity(intent);
        }
        catch (Exception e) {
        }
    }



}