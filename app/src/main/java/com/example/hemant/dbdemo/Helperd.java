package com.example.hemant.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hemant on 6/29/2016.
 */
public class Helperd extends SQLiteOpenHelper {

    public static final String Dname="mydb.db";
    public static final int Version=1;
    public Helperd(Context context){super(context,Dname,null,Version);}
    public static final String Table_name="Employee";
    public static final String ID="Id";
    public static final String Fname="First_name";
    public static final String Lname="Last_name";
    public static final String Address="Address";
    public static final String Salary="Salary";
    SQLiteDatabase mydb;


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable="Create Table "+Table_name+"("+ID+" INTEGER PRIMARY KEY, "+
                Fname+" TEXT NOT NULL, "+
                Lname+" TEXT NOT NULL, "+
                Salary+" REAL NOT NULL, "+
                Address+" TEXT NOT NULL "+")";
        db.execSQL(queryTable);
    }
    public void openDb()
    {mydb=getWritableDatabase();}
    public void closeDb()
    {if(mydb!=null&&mydb.isOpen()){
        mydb.close();

    }}
    public long insert(int Id,String Firstn,String Lastn,double sal,String Add){
            ContentValues values=new ContentValues();
        if(Id!=-1){
            values.put(ID,Id);
            values.put(Fname,Firstn);
            values.put(Lname,Lastn);
            values.put(Salary,sal);
            values.put(Address,Add);
        }return  mydb.insert(Table_name,null,values);
    }

    public long update(int Id,String Firstn,String Lastn,double sal,String Add){
        ContentValues values=new ContentValues();
        String where=Id+ " ="+ID;

        values.put(Fname,Firstn);
        values.put(Lname,Lastn);
        values.put(Salary,sal);
        values.put(Address,Add);

        return  mydb.update(Table_name,values,where,null);
    }

    public long delete(int Id) {
        ContentValues values = new ContentValues();
        String where = ID + " = " + Id;
        return mydb.delete(Table_name, where, null);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getAllRecords()
    {String query="Select * from "+Table_name;
    return mydb.rawQuery(query,null);}



    }
