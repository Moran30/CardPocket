package com.cardpocket.cardpocket;

import android.app.Activity;
import android.database.Cursor;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class forgottenpassword extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgottenpassword);
    }

    public void onClick(View view){

        // Show password once clicked
        if(view.getId() == R.id.bttn_showpassword) {

            EditText email = (EditText) findViewById(R.id.tf_emailaddress);
            TextView password = (TextView) findViewById(R.id.tv_userspassword);

            // Get the text from the fields
            String username = email.getText().toString();
            String Password ="";


            if (username.equals("")) {
                // Display...
                Toast.makeText(this, "Please enter an email address", Toast.LENGTH_LONG).show();

            }
            else {

DBAdapter db = new DBAdapter(this);
                //Open DB and Check User
                db.open();
                Cursor c = db.checkUser(username);

                //Create an arraylist
                ArrayList<String> theUser = new ArrayList<>();

                if (c.moveToFirst()) {
                    do{
                        // get the results from the database and put the password into a list
                        String aUser = c.getString(c.getColumnIndex("password"));
                        theUser.add(aUser);



                    } while (c.moveToNext());

                }
                // When no user is found
               if (theUser.size() ==0)
              //do nothing
               Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();

         if (theUser.size() ==1)
          //Get the password and then display it
            Password = theUser.get(0);
              password.setText(Password);

            }

        }


    }

}