package com.example.sqlitedatabase1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME="user.db";
    private   static String TABLE_NAME="user";
    public    static String COL_ID="id";
    public    static String COL_NAME="name";
    public    static String COL_AGE="age";

    //Create Version
    private static int VERSION=1 ;

    //Create Query
    private  String CREATE_TABLE="create table "+TABLE_NAME+" ("+COL_ID+" Integer Primary key autoincrement,"+COL_NAME+" TEXT,"+COL_AGE+" TEXT)";

    public SQLHelper(@Nullable Context context) {

        //name=DatabaseName
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Pass Parameter From Query.
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Create Custom Method
    //long id use kora hoy cz id ta save korar jonno
    //Insert Data
    public  long insertData(String name,String age){

        ContentValues contentValues=new ContentValues();
        //Data Put korte hobe
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_AGE,age);
        //Data get korte hobe
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
       long id= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        return id;
    }

    //ShowData
    public  Cursor  showData(){
        //Write Query
        String ALL_DATA_QUERY = "select * From " +TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(ALL_DATA_QUERY,null);
        return cursor;
    }

    //Search Data
    public Cursor searchData(int ID){
        String SEARCH_QUERY="select * From " +TABLE_NAME+" where "+COL_ID+" = "+ID;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(SEARCH_QUERY,null);
        return  cursor;
    }

    //Update Data
    public  boolean updateData(int id,String name,String age){

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_AGE,age);
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        sqLiteDatabase.update(TABLE_NAME,contentValues,COL_ID+"= ? ",new String[]{String.valueOf(id)});
        return true;
    }


    //Delete Data
    public  int  deleteData(int id){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
         int check=sqLiteDatabase.delete(TABLE_NAME,COL_ID+"= ? ",new String[]{String.valueOf(id)});
         return check;
    }
}
