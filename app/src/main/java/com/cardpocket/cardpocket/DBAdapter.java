package com.cardpocket.cardpocket;

/**
 * Created by Ashley on 01/02/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ROWID = "_id";

    // User Table
    static final String KEY_EMAIL = "email_address";
    static final String KEY_PASSWORD = "password";

   //Card Table
    static final String KEY_USER = "user";
    static final String KEY_CARDNAME = "card_name";
    static final String KEY_CARDNUMBER = "card_number";

    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "CPDB";
    //Database Tables
    static final String DATABASE_TABLE_USERS = "Users";
    static final String DATABASE_TABLE_CARDS = "Cards";

    static final int DATABASE_VERSION = 11;

  //Create the User Table
    static final String DATABASE_CREATE_USER =
            "create table Users (_id integer primary key autoincrement, "
                    + "email_address text,"
                      +"password text"
                        + ");";

 //Create the Card Table
    static final String DATABASE_CREATE_CARD =
            "create table Cards (_id integer primary key autoincrement, "
                    + "user text,"
                     + "card_name text,"
                      +"card_number text,"
                       +"FOREIGN KEY (user) REFERENCES Users (email_address)"
                        + ");";



    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter (Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context){
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //Execute the SQL to create the tables
        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(DATABASE_CREATE_USER);
                db.execSQL(DATABASE_CREATE_CARD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version" + oldVersion + "to" + newVersion + "which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Users");
            db.execSQL("DROP TABLE IF EXISTS Cards");
            onCreate (db);

        }
    }

    //--opens the DB--
    public DBAdapter open() throws SQLException
    {
        db= DBHelper.getWritableDatabase();
        return this;
    }

    //--close the DB--
    public void close()
    {
        DBHelper.close();
    }

    //--insert users into the DB--
    public long insertusers(String email, String password)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_PASSWORD, password);
        return db.insert(DATABASE_TABLE_USERS, null, initialValues);
    }

    //--deletes a chosen user--
    public boolean deleteContact(long rowId){
        return db.delete(DATABASE_TABLE_USERS, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //--retrieves all users--
    public Cursor getAllUsers(){
        return db.query(DATABASE_TABLE_USERS, new String[] {KEY_ROWID, KEY_EMAIL, KEY_PASSWORD}, null,null,null,null,null,null);

    }


    //--retrieves chosen user--
    public Cursor getContact(long rowId) throws SQLException{
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_USERS, new String[] {KEY_ROWID, KEY_EMAIL}, KEY_ROWID + "=" + rowId, null, null, null, null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //--updates a user--
    public boolean updateContact(long rowId, String email, String password)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EMAIL, email);
        args.put(KEY_PASSWORD, password);
        return db.update(DATABASE_TABLE_USERS, args, KEY_ROWID + "=" + rowId, null) >0;

    }

    //--retrieves chosen user--
    public Cursor checkUser(String username) throws SQLException{
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_USERS, new String[] {KEY_ROWID, KEY_EMAIL, KEY_PASSWORD}, KEY_EMAIL + "= '" + username + "'", null, null, null, null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    //--insert cards into the DB--
    public long insertCard(String cardnum, String cardname, String usersname)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CARDNUMBER, cardnum);
        initialValues.put(KEY_CARDNAME, cardname);
        initialValues.put(KEY_USER, usersname);
        return db.insert(DATABASE_TABLE_CARDS, null, initialValues);
    }


    //--retrieves cards for the user--
    public Cursor getCards(String username) throws SQLException{
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_CARDS, new String[] {KEY_ROWID, KEY_USER, KEY_CARDNAME}, KEY_USER + "= '" + username + "'", null, null, null, null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //--retrieves cardname for the user--
    public Cursor viewCard(String cardname) throws SQLException{
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_CARDS, new String[] {KEY_ROWID, KEY_CARDNAME, KEY_CARDNUMBER}, KEY_CARDNAME + "= '" + cardname + "'", null, null, null, null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //--deletes a chosen card--
    public boolean deleteCard(long rowId){
        return db.delete(DATABASE_TABLE_CARDS, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //--updates a user--
    public boolean updateEmail(String email, String newemail)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_EMAIL, newemail);
        return db.update(DATABASE_TABLE_USERS, args, KEY_EMAIL + "= '" + email +"'", null) >0;

    }


    //--updates a user--
    public boolean updatePassword(String email, String newpass)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_PASSWORD, newpass);
        return db.update(DATABASE_TABLE_USERS, args, KEY_EMAIL + "= '" + email +"'", null) >0;

    }

    //--updates a card--
    public boolean updatecard(String cardname, String newcardname)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_CARDNAME, newcardname);
        return db.update(DATABASE_TABLE_CARDS, args, KEY_CARDNAME + "= '" + cardname +"'", null) >0;

    }

}
