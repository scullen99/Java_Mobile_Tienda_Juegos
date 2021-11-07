package com.example.tablayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tablayout.R;

import java.util.ArrayList;

public class BBDD extends SQLiteOpenHelper {
    private static final String DBNAME = "gamesdatabase";
    private static final int DBVERSION = 2;


    public BBDD(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE NEWGAMES ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "COMPANYGAMES TEXT,"
                + "FECHA INTEGER,"
                + "IMAGE_ID INTEGER, "
                + "FAVORITE INTEGER, "
                + "PRIZE INTEGER, "
                + "PLATFORM TEXT, "
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");


        db.execSQL("CREATE TABLE XBOX ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "COMPANYGAMES TEXT,"
                + "IMAGE_ID INTEGER, "
                + "FAVORITE INTEGER, "
                + "PRIZE INTEGER, "
                + "PLATFORM TEXT, "
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");

        db.execSQL("CREATE TABLE PS4 ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "COMPANYGAMES TEXT,"
                + "IMAGE_ID INTEGER, "
                + "FAVORITE INTEGER, "
                + "PRIZE INTEGER, "
                + "PLATFORM TEXT, "
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");


        db.execSQL("CREATE TABLE OFERTAS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "COMPANY TEXT, "
                + "COMPANYGAMES TEXT,"
                + "IMAGE_ID INTEGER, "
                + "FAVORITE INTEGER, "
                + "PRIZE INTEGER, "
                + "PLATFORM TEXT, "
                + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");


        db.execSQL("CREATE TABLE pedido ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT,"
                + "COMPANY TEXT);");



        //addPedido (db, "OUTCAST","APPEAL");

        addNewVideogame (db, "OUTCAST",2020,"APPEAL","PS4",R.drawable.ic_launcher_background);
        addNewVideogame (db, "SHENMUE",2020,"SEGA", "PS4" ,R.drawable.ic_launcher_background);
        addNewVideogame (db, "NINOKUNI II",2020,"LEVEL5", "PS4",R.drawable.ic_launcher_background);


        addVideogameXBOX (db, "Kingdom Hearts","Square Enix","XBOX",R.drawable.ic_launcher_background);
        addVideogameXBOX (db, "Call of duty","Treyarch", "XBOX" ,R.drawable.ic_launcher_background);
        addVideogameXBOX (db, "Metro exodus","4A Games", "XBOX",R.drawable.ic_launcher_background);


        addVideogamePS4 (db, "SPIDERMAN","INSOMIAC GAMES","PS4",R.drawable.ic_launcher_background);
        addVideogamePS4 (db, "UNCHARTED 4","NAUGHTY DOG", "PS4" ,R.drawable.ic_launcher_background);
        addVideogamePS4 (db, "GOD OF WAR","SONY SANTA MONICA", "PS4",R.drawable.ic_launcher_background);


        addVideogameOfertas (db, "TITANFALL 2","NADA","PS4",R.drawable.ic_launcher_background);
        addVideogameOfertas (db, "NARUTO STORM 4","NADA 2", "PS4" ,R.drawable.ic_launcher_background);
        addVideogameOfertas (db, "FIFA 22","NADA 3", "PS4",R.drawable.ic_launcher_background);

    }



    public static void addNewVideogame (SQLiteDatabase db, String name, int fecha ,String company,String companyGame, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("FECHA", fecha);
        gamesData.put("COMPANY", company);
        gamesData.put("COMPANYGAMES", companyGame);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("NEWGAMES", null, gamesData);
    }

    public static void addVideogameXBOX (SQLiteDatabase db, String name, String company,String companyGame, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("COMPANYGAMES", companyGame);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("XBOX", null, gamesData);
    }


    public static void addVideogamePS4 (SQLiteDatabase db, String name, String company,String companyGame, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("COMPANYGAMES", companyGame);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("PS4", null, gamesData);
    }


    public static void addPedido (SQLiteDatabase db, String name, String company)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        db.insert("pedido", null, gamesData);
    }

    public static void addVideogameOfertas (SQLiteDatabase db, String name, String company,String companyGame, int imageID)
    {
        ContentValues gamesData = new ContentValues();
        gamesData.put("NAME", name);
        gamesData.put("COMPANY", company);
        gamesData.put("COMPANYGAMES", companyGame);
        gamesData.put("IMAGE_ID", imageID);
        db.insert("OFERTAS", null, gamesData);
    }

    public static void eliminarPedido (SQLiteDatabase db,Integer id)
    {
        db.delete("pedido","_id"+"="+id,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion == 1 && newVersion == 2)
        {
            db.execSQL("ALTER TABLE NEWGAMES ADD COLUMN FAVORITE BIT DEFAULT 0");
            db.execSQL("ALTER TABLE XBOX ADD COLUMN FAVORITE BIT DEFAULT 0");
            db.execSQL("ALTER TABLE PS4 ADD COLUMN FAVORITE BIT DEFAULT 0");
            db.execSQL("ALTER TABLE pedido ADD COLUMN FAVORITE BIT DEFAULT 0");
        }
    }
}
