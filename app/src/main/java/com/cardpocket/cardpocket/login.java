package com.cardpocket.cardpocket;

import android.app.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class login extends Activity {
    Button btn1,btn2;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        /* Called when the activity is first created*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1 = (Button)findViewById(R.id.bttn_signup);
        btn2 = (Button)findViewById(R.id.bttn_signin);
    }

    public void onClick(View view){


if(view.getId() ==R.id.tv_forgot) {
    //Go to the forgotten password activity
    startActivity(new Intent("com.cardpocket.cardpocket.forgot"));
}

  // On Click go to Sign up
        if(view.getId() == R.id.bttn_signup) {
            startActivity(new Intent("com.cardpocket.cardpocket.signup"));
        }

        // On Click attempt to sign in
        if(view.getId() == R.id.bttn_signin) {

            EditText email = (EditText) findViewById(R.id.tf_email);
            EditText pass = (EditText) findViewById(R.id.tf_password);

            // Get the text from the fields
            String username = email.getText().toString();
            String password = pass.getText().toString();


            DBAdapter db = new DBAdapter(this);
           // Open DB and check user
            db.open();
            Cursor c = db.checkUser(username);

            // Create an arraylist
            ArrayList<String> theUser = new ArrayList<>();


            if (c.moveToFirst()) {
                do {
                    //get results from the database and put all passwords in the list
                    String aUser = c.getString(c.getColumnIndex("password"));
                    theUser.add(aUser);


                } while (c.moveToNext());
            }
            //if the list contains the password entered then..
            if (theUser.contains(password))

            {  //set the value and pass to the mainactivity
                Intent i = new Intent(this, mainactivity.class);
                i.putExtra("username", username);
                startActivity(i);

                // when the user presses back the app will end
            finish();
            }
         else {
                // display..
                Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
            }

        }


        }
    }






