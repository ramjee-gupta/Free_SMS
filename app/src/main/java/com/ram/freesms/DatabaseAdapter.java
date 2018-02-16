package com.ram.freesms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by RAMJEE on 09-02-2018.
 */

public class DatabaseAdapter  {

     SMSDatabaseHelper helper;

    public DatabaseAdapter(Context context){
        helper = new SMSDatabaseHelper(context);

    }
   public long insertData(String name, String message){

       SQLiteDatabase  db = helper.getWritableDatabase();

       ContentValues contentValues = new ContentValues();
       contentValues.put(SMSDatabaseHelper.Name,name);
       contentValues.put(SMSDatabaseHelper.Message,message);

       long id =  db.insert(SMSDatabaseHelper.TABLE_NAME,null,contentValues);
       db.close();
       return id;

   }

    public  ArrayList<SingleSmsRow> getData(ArrayList<SingleSmsRow> mList) {

        SQLiteDatabase db = helper.getWritableDatabase();
       String columns[] = {SMSDatabaseHelper.UID,SMSDatabaseHelper.Name,SMSDatabaseHelper.Message};
        Cursor cursor = db.query(SMSDatabaseHelper.TABLE_NAME,columns,null,null,null,null,null );

        while (cursor.moveToNext()){
            int uid = cursor.getInt(0);
            String number = cursor.getString(1);
            String message = cursor.getString(2);
            mList.add(new SingleSmsRow(number,message));
        }

        db.close();
        return mList;
    }



    static class SMSDatabaseHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "SMS_STORAGE";
         static final String TABLE_NAME = "SMS";
        private static  final int VERSION = 1;
        private static  final String UID= "_id";
        private static  final String Name= "Name";
        private static  final String Message = "Message";
        private Context context;

         SMSDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);

            this.context = context;
             Toast.makeText(context,"Constructure called",Toast.LENGTH_SHORT).show();
         }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            String  query = "CREATE TABLE " +TABLE_NAME+ "("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Name+" VARCHAR(40), "+Message+" VARCHAR(255));";

            try {
                sqLiteDatabase.execSQL(query);
                Toast.makeText(context,"Table created",Toast.LENGTH_SHORT).show();
            }catch (SQLException e){
                e.printStackTrace();


            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }



    }



}
